package faddy.backend.image.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ImageUploader {

    String upload(MultipartFile multipartFile) throws IOException;

    void deleteFile(String fileName);

    void deleteFiles(List<String> fileNames);

    String updateFile(MultipartFile newFile, String oldFileName) throws IOException;

    String extractObjectKeyFromObjUrl(String objUrl) throws MalformedURLException;
}
