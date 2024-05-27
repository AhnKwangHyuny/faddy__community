package faddy.backend.image.infrastructure;


import faddy.backend.image.domain.Image;
import faddy.backend.image.dto.ImageResponseDto;
import faddy.backend.snap.domain.Snap;

public class ImageMapper {
    public static Image uploadImageResponseToEntity(ImageResponseDto response) {
        return new Image(response.getUrl(),
                response.getHashedName(),
                response.getOriginalName(),
                response.getSize(),
                response.getFormat(),
                response.getCategory());
    }
}
