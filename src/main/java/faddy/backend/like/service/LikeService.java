package faddy.backend.like.service;

import java.util.Set;

public interface LikeService {
    String getLikeCount(Long snapId);

    void like(Long userId, Long snapId);

    void unlike(Long userId, Long snapId);

    Set<String> getUserIds(Long snapId);

    boolean hasLiked(Long userId, Long snapId);

}
