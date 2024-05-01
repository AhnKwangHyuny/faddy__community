package faddy.backend.global.scheduler;

import faddy.backend.like.repository.LikeJpaRepository;
import faddy.backend.like.service.LikeService;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.repository.SnapRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeScheduler {
    private final LikeService likeService;
    private final LikeJpaRepository likeRepository;
    private final SnapRepository snapRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedDelay = 120000)
    public void refreshRedisData() {
        String lastSyncKey = "last_sync_time";
        Long lastSyncTime = redisTemplate.opsForValue().get(lastSyncKey) != null ? Long.parseLong(redisTemplate.opsForValue().get(lastSyncKey).toString()) : 0L;

        List<Snap> updatedSnaps = snapRepository.findByUpdatedAtGreaterThanOrderByUpdatedAtAsc(new Date(lastSyncTime));

        for (Snap snap : updatedSnaps) {
            String key = "snap:" + snap.getId();

            Long likeCount = likeRepository.countBySnap(snap);
            List<User> users = likeRepository.findUsersBySnap(snap);

            Set<String> userIds = users.stream()
                    .map(user -> user.getId().toString())
                    .collect(Collectors.toSet());

            try {
                redisTemplate.opsForHash().put(key, "likeCount", likeCount);
                redisTemplate.opsForSet().add(key + ":userIds", userIds.toArray(new String[0]));

            } catch (Exception e) {
                redisTemplate.delete(key);
                redisTemplate.delete(key + ":userIds");
                throw e;
            }
        }

        redisTemplate.opsForValue().set(lastSyncKey, System.currentTimeMillis());
    }
}