package faddy.backend.like.repository;

import faddy.backend.like.domain.Like;

import java.util.List;

public interface LikeCustomRepository {

    List<Long> batchSaveLikesForSnaps(List<Like> likes);
    List<Long> batchSaveLikesForStyleBoards(List<Like> likes);
    List<Long> batchSaveLikesForStyleBoardComments(List<Like> likes);

}
