package faddy.backend.styleBoardComment.service.useCase;

import faddy.backend.styleBoardComment.dto.response.find.StyleBoardCommentResponseDTO;

import java.util.List;

public interface GetStyleBoardCommentService {

    /**
     * 특정 스타일보드 댓글 조회
     * @param styleBoardId
     * @return List<StyleBoardCommentResponseDTO>
     */
    List<StyleBoardCommentResponseDTO> findByStyleBoardIdWithReplies(Long styleBoardId);

}
