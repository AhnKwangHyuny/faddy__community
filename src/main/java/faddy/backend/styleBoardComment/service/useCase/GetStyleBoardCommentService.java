package faddy.backend.styleBoardComment.service.useCase;

import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.styleBoardComment.dto.response.find.StyleBoardCommentResponseDTO;

import java.util.List;

public interface GetStyleBoardCommentService {

    /**
     * 특정 스타일보드 댓글 조회
     * @param styleBoardId
     * @param userId // 로그인 유저의 id , 비로그인 && 말료된 토큰 유저 요청 시 null
     * @return List<StyleBoardCommentResponseDTO>
     */
    List<StyleBoardCommentResponseDTO> findByStyleBoardIdWithReplies(Long styleBoardId , Long userId);


    /**
     * 특정 스타일보드 댓글 조회 (댓글 연관관계 엔티티는 제외 , lazy)
     * @param styleBoardCommentId
     * @return StyleBoardComment
     */
    StyleBoardComment loadStyleBoardById(Long styleBoardCommentId);
}
