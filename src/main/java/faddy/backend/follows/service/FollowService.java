package faddy.backend.follows.service;

import faddy.backend.follows.domain.Follow;
import faddy.backend.follows.repository.FollowJpaRepository;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.FollowException;
import faddy.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowJpaRepository followRepository;

    /**
     * follow 요청
     * @Param User 팔로우 요청 유저 , User 팔로우 받은 유저
     * @Return 요청 결과 message
     * */
    public String follow(User follower , User followee) {
        // 자기 자신 follow 안됨
        if (follower == followee)
            throw new FollowException(ExceptionCode.FAIL_SELF_FOLLOW_ERROR);

        // 중복 follow x
        if (followRepository.existsByFollowerAndFollowee(follower , followee)){
            throw new FollowException(ExceptionCode.ALREADY_FOLLOW_ERROR);
        }

        // Follow 엔티티 생성
        Follow follow = Follow.builder()
                .toUser(followee)
                .fromUser(follower)
                .build();
        followRepository.save(follow);

        return "팔로우에 성공했습니다. (상대방에게 메세지 전송)";
    }

}
