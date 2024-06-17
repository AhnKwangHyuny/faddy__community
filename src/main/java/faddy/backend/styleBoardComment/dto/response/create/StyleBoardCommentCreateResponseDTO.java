package faddy.backend.styleBoardComment.dto.response.create;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StyleBoardCommentCreateResponseDTO {
    private Long id;
    private UserDTO author;
    private String content;
    private LocalDateTime createdAt;
    private int likeCount;

}