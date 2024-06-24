package faddy.backend.like.service.useCase;

import faddy.backend.comment.domain.Comment;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.user.domain.User;

import java.util.List;
import java.util.Set;

public interface LikeService {
    String getLikeCount(Long snapId);

    void like(Long userId, Long snapId);

    void unlike(Long userId, Long snapId);

    Set<String> getUserIds(Long snapId);

    boolean hasLiked(Long userId, Long snapId);

    void createLike(String objectType, Long objectId , String token);

    void deleteLike(String objectType, Long objectId);



    /**
     * Comment에 좋아요를 저장합니다.
     * @param StyleBoardComment 좋아요를 누른 Comment
     * @param users 좋아요를 누른 사용자 목록
     */
    void batchSaveStyleBoardCommentLikes(StyleBoardComment comment, List<User> users);


    /**
     * styleBoard likes를 생성 후 배치 저장 수행
     * @param styleBoard 좋아요를 누른 StyleBoard
     * @param users 좋아요를 누른 사용자 목록
     * */
    void batchSaveStyleBoardLikes(StyleBoard styleBoard , List<User> users);
}
