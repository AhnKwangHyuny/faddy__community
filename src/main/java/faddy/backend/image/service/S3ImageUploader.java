package faddy.backend.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ImageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class S3ImageUploader implements ImageUploader{

    private final AmazonS3 amazonS3;
    private final String bucket;

    private final String directory;
    private final String region;

    private final AmazonS3 s3Client;

    private static final String EXTENSION_DELIMITER = ".";
    private static final String DIRECTORY_DELIMITER = "/";

    public S3ImageUploader(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket ,
                           @Value("${cloud.aws.s3.dir}") String directory ,
                           @Value("${cloud.aws.region.static}") String region,
                           AmazonS3 s3Client) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
        this.directory = directory;
        this.region = region;
        this.s3Client =s3Client;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        // 파일 이름에서 공백을 제거한 새로운 파일 이름 생성
        String originalFileName = multipartFile.getOriginalFilename();

        // UUID를 파일명에 추가
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid + "_" + originalFileName.replaceAll("\\s", "_");

        String fileName = this.directory + DIRECTORY_DELIMITER + uniqueFileName;

        File uploadFile = convert(multipartFile);

        String objUrl = putS3(uploadFile, fileName);

        log.info("objUrl : " + objUrl);

        removeNewFile(uploadFile);

        return objUrl;
    }


    private File convert(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String uniqueFileName = uuid + "_" + originalFileName.replaceAll("\\s", "_");

        File convertFile = new File(uniqueFileName);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                throw new ImageException(ExceptionCode.IMAGE_CONVERT_ERROR);
            }
            return convertFile;
        }
        throw new IllegalArgumentException(String.format("파일 변환에 실패했습니다. %s", originalFileName));
    }

    private String putS3(File uploadFile, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHttpExpiresDate(new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000));

        PutObjectRequest request = new PutObjectRequest(bucket, fileName, uploadFile);
        request.setMetadata(metadata);
        request.setCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(request);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName);
        long expirationTime = System.currentTimeMillis() + ( 6 * 24 * 60 * 60 * 1000L);
        Date expiration = new Date(expirationTime);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL objUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return objUrl.toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }
    @Override
    public void deleteFile(String fileName) {
        try {
            // URL 디코딩을 통해 원래의 파일 이름을 가져옵니다.
            String decodedFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("Deleting file from S3: " + decodedFileName);
            amazonS3.deleteObject(bucket, decodedFileName);

        } catch (UnsupportedEncodingException e) {
            log.error("Error while decoding the file name: {}", e.getMessage());

            throw new ImageException(HttpStatus.INTERNAL_SERVER_ERROR , "Error while decoding the file name: " + e.getMessage());
        }
    }

    @Override
    public void deleteFiles(List<String> fileNames) {
        for (String fileName : fileNames) {
            try {
                String decodedFileName = URLDecoder.decode(fileName, "UTF-8");
                log.info("Deleting file from S3: {}", decodedFileName);
                amazonS3.deleteObject(bucket, decodedFileName);

            } catch (UnsupportedEncodingException e) {
                log.error("Error while decoding the file name: {}", e.getMessage());
                throw new ImageException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while decoding the file name: " + e.getMessage());
            }
        }
    }

    @Override

    public String updateFile(MultipartFile newFile, String oldFileName) throws IOException {
        // 기존 파일 삭제
        log.info("S3 oldFileName: " + oldFileName);
        deleteFile(oldFileName);
        // 새 파일 업로드
        return upload(newFile);
    }

    // s3 객체 url 생성
    private String createObjectUrl(String fileName) {
        return new StringBuilder()
                .append("https://")
                .append(bucket)
                .append(".s3.")
                .append(region)
                .append(".amazonaws.com/")
                .append(directory)
                .append("/")
                .append(fileName)
                .toString();
    }

    public URL getObjectUrl(String bucketName, String objectKey) {
        // 객체 URL 생성을 위한 요청 생성
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectKey);
        // 서명된 URL 생성
        return s3Client.generatePresignedUrl(request);
    }



    @Override
    public String extractObjectKeyFromObjUrl(String objUrl) throws MalformedURLException {
        URL url = new URL(objUrl);
        // URL에서 path 부분을 가져옵니다. 첫 번째 '/'를 제외한 나머지 부분이 객체 키입니다.
        String path = url.getPath().substring(1); // 첫 번째 '/' 제거
        return path;
    }
}