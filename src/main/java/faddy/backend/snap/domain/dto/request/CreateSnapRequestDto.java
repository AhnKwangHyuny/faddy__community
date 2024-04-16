package faddy.backend.snap.domain.dto.request;

import java.util.List;
import java.util.Map;

import faddy.backend.type.ContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateSnapRequestDto {

    @NotBlank(message = "사용자 ID는 비어 있을 수 없습니다.")
    private String userId;

    @NotEmpty(message = "이미지 목록은 비어 있을 수 없습니다.")
    private List<ImageDto> imageList;

    private List<String> hashTagIds;

    private CategoryDto categoryIds;

    @Size(max = 1500, message = "설명은 1500자를 초과할 수 없습니다.")
    private String description;

    // ImageDto 클래스
    @Getter
    public static class ImageDto {

        @NotBlank(message = "해시된 이름은 비어 있을 수 없습니다.")
        private String hashedName;

        @NotBlank(message = "이미지 URL은 비어 있을 수 없습니다.")
        @Size(max = 1024, message = "이미지 URL은 1024자를 초과할 수 없습니다.")
        private String url;
    }

    @Getter
    public static class CategoryDto {

        private ContentType contentType;
        private List<Map<String, String>> categories;
    }
}
