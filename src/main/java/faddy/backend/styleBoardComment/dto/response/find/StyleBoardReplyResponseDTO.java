package faddy.backend.styleBoardComment.dto.response.find;

import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleBoardReplyResponseDTO {

    private Long id;
    private String content;
    private boolean isDeleted = false; // 초기 값 false 설정
    private Long parentId;
    private UserDTO author;
    private LocalDateTime created_at;

    public static StyleBoardReplyResponseDTO from(StyleBoardComment reply) {
        return StyleBoardReplyResponseDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .isDeleted(reply.isDeleted())
                .parentId(reply.getParent().getId())
                .author(UserDTO.from(reply.getAuthor()))
                .created_at(reply.getCreated_at())
                .build();
    }

}
