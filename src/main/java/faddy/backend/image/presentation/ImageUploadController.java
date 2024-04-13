package faddy.backend.image.presentation;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ImageException;
import faddy.backend.image.dto.ImageResponseDto;
import faddy.backend.image.dto.UploadedImageResponse;
import faddy.backend.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageUploadController {

    private final ImageService imageService;

    @PostMapping("/images")
    public ResponseEntity<ImageResponseDto> handleImageUpload(@RequestParam("image") MultipartFile file) throws IOException {
        // 파일 크기 검증 (10MB)
        if (file.getSize() > 10_000_000) { // 10MB
            throw new ImageException(ExceptionCode.FILE_SIZE_TOO_LARGE);
        }

        // 파일 형식 검증 (JPEG, PNG)
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            throw new ImageException(ExceptionCode.INVALID_AUTH_CODE);
        }

        ImageResponseDto imageResponseDto = imageService.uploadImage(file);

        UploadedImageResponse.of(imageResponseDto.getHashedName(), imageResponseDto.getUrl() );

        return ResponseEntity.created(URI.create(imageResponseDto.getUrl())).body(imageResponseDto);
    }

}
