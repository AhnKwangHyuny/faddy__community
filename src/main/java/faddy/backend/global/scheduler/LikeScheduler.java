package faddy.backend.global.scheduler;

import faddy.backend.like.repository.LikeJpaRepository;
import faddy.backend.like.service.adapter.useCase.LikeCountSyncAdapter;
import faddy.backend.like.service.useCase.LikeService;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.repository.SnapRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeScheduler {
    private final LikeCountSyncAdapter likeCountSyncAdapter;

    /**
     *  styleBoard 좋아요 수 동기화
     *
     * */
    @Scheduled(fixedRate =60000 )
    public void updateStyleBoardLikes() {
        log.info("좋아요 동기화 작업을 시작합니다.");
        likeCountSyncAdapter.updateStyleBoardLikeInDatabase();
        log.info("좋아요 동기화 작업을 완료했습니다.");
    }


}