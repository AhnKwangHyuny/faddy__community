package faddy.backend.image.presentation;

import faddy.backend.image.dto.ImageRequestDto;
import faddy.backend.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;

    @DeleteMapping("/images")
    public ResponseEntity<Void> handleImageDelete(@RequestBody ImageRequestDto imageRequestDto) {
        imageService.deleteImage(imageRequestDto.getHashedName());


        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/images/multiple")
    public ResponseEntity<Void> handleImageListDelete(@RequestBody List<ImageRequestDto> images) {


        imageService.deleteImages(images);

        return ResponseEntity.noContent().build();
    }


}
