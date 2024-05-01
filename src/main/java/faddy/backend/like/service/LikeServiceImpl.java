package faddy.backend.like.service;

import faddy.backend.like.domain.Like;
import faddy.backend.like.repository.LikeJpaRepository;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.repository.SnapRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final LikeJpaRepository likeRepository;
    private final UserRepository userRepository;
    private final SnapRepository snapRepository;

    @Transactional(readOnly = true)
    @Override
    public Long getLikeCount(Long snapId) {
        // Read Through 전략
        String key = "snap:" + snapId;
        Object likeData = redisTemplate.opsForHash().get(key, "likeCount");

        // 캐싱 실패 -> DB 조회
        if (likeData == null) {
            Snap snap = snapRepository.findById(snapId).orElseThrow();
            Long likeCount = likeRepository.countBySnap(snap);

            // Redis에 좋아요 수 캐싱
            redisTemplate.opsForHash().put(key, "likeCount", likeCount);

            // Redis에서 Set을 사용하여 userIds 관리
            Set<String> userIds = likeRepository.findBySnap(snap).stream()
                    .map(like -> like.getUser().getId().toString())
                    .collect(Collectors.toSet());

            redisTemplate.opsForSet().add(key + ":userIds", userIds.toArray(new String[0]));

            return likeCount;
        }
        return Long.parseLong(likeData.toString());
    }


    @Override
    @Transactional
    public void like(Long userId, Long snapId) {
        // write through 전략
        String key = "snap:" + snapId;
        User user = userRepository.findById(userId).orElseThrow();
        Snap snap = snapRepository.findById(snapId).orElseThrow();

        // DB에 데이터 저장
        Like like = new Like(user, snap);

        // 연관관계 설정 (user <-> like, like -> snap)
        user.addLike(like);
        likeRepository.save(like);

        // Redis에 좋아요 수 저장
        // 'likeCount'를 1 증가시킴
        redisTemplate.opsForHash().increment(key, "likeCount", 1);

        // Redis에 사용자 ID 저장
        redisTemplate.opsForSet().add(key + ":userIds", String.valueOf(userId));


    }

    @Override
    @Transactional
    public void unlike(Long userId, Long snapId) {

        // Write Through 패턴
        String key = "snap:" + snapId;
        User user = userRepository.findById(userId).orElseThrow();
        Snap snap = snapRepository.findById(snapId).orElseThrow();

        // db에서 데이터 삭제
        Like like = likeRepository.findByUserAndSnap(user, snap).orElseThrow();
        likeRepository.delete(like);

        // Redis에서 좋아요 수 감소
        redisTemplate.opsForHash().increment(key, "likeCount", -1);

        // Redis Set에서 사용자 ID 삭제
        redisTemplate.opsForSet().remove(key + ":userIds", String.valueOf(userId));

        // 모든 사용자가 좋아요를 취소한 경우, 좋아요 수 확인 후 Set과 Hash 키 삭제
        String likeCountStr = (String) redisTemplate.opsForHash().get(key, "likeCount");

        Long likeCount = Long.parseLong(likeCountStr);

        if (likeCount == null || likeCount <= 0) {

            redisTemplate.delete(Arrays.asList(key, key + ":userIds"));
        }
    }

    @Override
    public Set<String> getUserIds(Long snapId) {

        String key = "snap:" + snapId + ":userIds";

        Set<Object> userIds = redisTemplate.opsForSet().members(key);

        // userIds가 null이 아닌 경우 해당 값을 반환하고, 그렇지 않으면 빈 Set을 반환
        return userIds != null ? userIds.stream().map(Object::toString).collect(Collectors.toSet()) : Collections.emptySet();
    }

    @Override
    public boolean hasLiked(Long userId, Long snapId) {

        String key = "snap:" + snapId + ":userIds";
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, String.valueOf(userId)));
    }
}