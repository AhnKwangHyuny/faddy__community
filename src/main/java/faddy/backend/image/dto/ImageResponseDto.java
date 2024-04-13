package faddy.backend.image.dto;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ImageException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ImageResponseDto {
    private String originalName; // 원본 파일명
    private Long size; // 파일 크기
    private String format; // 파일 형식
    private String url; // 파일 URL
    private String hashedName; // 해시된 파일명

    // 기본 생성자
    public ImageResponseDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public ImageResponseDto(String originalName, Long size, String format, String url, String hashedName) {
        this.originalName = originalName;
        this.size = size;
        this.format = format;
        this.url = url;
        this.hashedName = hashedName;
    }

    // ImageFile 객체로부터 ImageResponseDto 객체를 생성하는 정적 메소드
    public static ImageResponseDto fromImageFile(ImageFile imageFile) {

        try {
            return new ImageResponseDto(
                    imageFile.getOriginalName(),
                    imageFile.getSize(),
                    imageFile.getFormat(),
                    imageFile.getUrl(),
                    imageFile.getHashedName()
            );
        } catch (Exception e) {
            throw new ImageException(ExceptionCode.FAIL_IMAGE_NAME_HASH);
        }

    }
}
