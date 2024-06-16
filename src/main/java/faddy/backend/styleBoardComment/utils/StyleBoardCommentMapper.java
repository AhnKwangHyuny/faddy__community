package faddy.backend.styleBoardComment.utils;

import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.styleBoardComment.dto.response.create.StyleBoardCommentCreateResponseDTO;
import faddy.backend.styleBoardComment.dto.response.create.UserDTO;

public class StyleBoardCommentMapper {

    // savedComment to StyleBoardCommentCreateResponseDTO
    public static StyleBoardCommentCreateResponseDTO toDto(StyleBoardComment comment) {
        if (comment == null) {
            return null;
        }

        UserDTO user = UserDTO.builder()
                .level(comment.getAuthor().getProfile().getUserLevel())
                .profileImageUrl(comment.getAuthor().getProfile().getProfileImageUrl())
                .nickname(comment.getAuthor().getNickname())
                .build();

        return StyleBoardCommentCreateResponseDTO.builder()
                .id(comment.getId())
                .author(user)
                .content(comment.getContent())
                .createdAt(comment.getCreated_at())
                .build();
    }


}
