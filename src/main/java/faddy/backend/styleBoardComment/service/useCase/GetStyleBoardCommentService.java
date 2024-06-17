package faddy.backend.styleBoardComment.service.useCase;

import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.styleBoardComment.dto.response.find.StyleBoardCommentResponseDTO;

import java.util.List;

public interface GetStyleBoardCommentService {

    /**
     * 특정 스타일보드 댓글 조회
     * @param styleBoardId
     * @return List<StyleBoardCommentResponseDTO>
     */
    List<StyleBoardCommentResponseDTO> findByStyleBoardIdWithReplies(Long styleBoardId);


    /**
     * 특정 스타일보드 댓글 조회 (댓글 연관관계 엔티티는 제외 , lazy)
     * @param styleBoardCommentId
     * @return StyleBoardComment
     */
    StyleBoardComment loadStyleBoardById(Long styleBoardCommentId);
}
