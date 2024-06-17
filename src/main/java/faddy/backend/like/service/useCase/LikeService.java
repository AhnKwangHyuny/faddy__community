package faddy.backend.like.service.useCase;

import java.util.Set;

public interface LikeService {
    String getLikeCount(Long snapId);

    void like(Long userId, Long snapId);

    void unlike(Long userId, Long snapId);

    Set<String> getUserIds(Long snapId);

    boolean hasLiked(Long userId, Long snapId);

    void createLike(String objectType, Long objectId , String token);

    void deleteLike(String objectType, Long objectId);
}
