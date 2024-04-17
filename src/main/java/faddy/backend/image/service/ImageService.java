package faddy.backend.image.service;

import faddy.backend.image.domain.Image;
import faddy.backend.image.dto.ImageRequestDto;
import faddy.backend.image.dto.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {


    ImageResponseDto uploadImage (MultipartFile file) throws IOException;

    void saveImage(ImageResponseDto dto);

    void validateImageFile(MultipartFile file);

    void deleteImage(String hashName);

    void deleteImages(List<ImageRequestDto> images);

    Image findById(Long imageId);

    List<Image> findByHashedNames(List<String> hashedNames);
}
