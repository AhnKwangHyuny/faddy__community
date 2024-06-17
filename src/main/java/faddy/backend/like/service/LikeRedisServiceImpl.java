package faddy.backend.like.service;


import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.like.service.useCase.LikeRedisService;
import faddy.backend.like.type.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeRedisServiceImpl implements LikeRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private String generateRedisKey(ContentType contentType, Long objectId) {
        return contentType.name().toLowerCase() + ":" + objectId;
    }


    @Override
    @Transactional
    public void initializeLikes(Long objectId, ContentType contentType) {
        String redisKey = generateRedisKey(contentType, objectId);
        System.out.println("redisKey = " + redisKey);
        redisTemplate.opsForSet().add(redisKey);
    }

    @Override
    @Transactional
    public void saveLike(Long objectId, Long userId, ContentType contentType) {

        try {

            String redisKey = generateRedisKey(contentType, objectId);
            Long result = redisTemplate.opsForSet().add(redisKey, userId.toString());
            if (result == 0) {
                throw new SaveEntityException(HttpStatus.BAD_REQUEST, "이미 좋아요를 누른 유저입니다.");
            }

        } catch (Exception e) {
            throw new SaveEntityException(HttpStatus.BAD_REQUEST , "[redis] 좋아요 저장에 실패했습니다.");
        }

    }

    @Override
    @Transactional
    public void removeLike(Long objectId, Long userId, ContentType contentType) {
        try {
            String redisKey = generateRedisKey(contentType, objectId);
            redisTemplate.opsForSet().remove(redisKey, userId.toString());
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value() , "[redis] 좋아요 삭제에 실패했습니다.");
        }
    }

    @Override
    public boolean isLiked(Long objectId, Long userId, ContentType contentType) {
        String redisKey = generateRedisKey(contentType, objectId);
        return redisTemplate.opsForSet().isMember(redisKey, userId.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public int countLikes(Long objectId, ContentType contentType) {
        String redisKey = generateRedisKey(contentType, objectId);
        Long size = redisTemplate.opsForSet().size(redisKey);
        return size != null ? size.intValue() : 0;
    }
}
