package faddy.backend.follows.service;

import faddy.backend.follows.domain.Follow;
import faddy.backend.follows.repository.FollowJpaRepository;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.FollowException;
import faddy.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowJpaRepository followRepository;

    /**
     * follow 요청
     * @Param User 팔로우 요청 유저 , User 팔로우 받은 유저
     * @Return 요청 결과 message
     * */
    @Transactional
    public String follow(User following , User follower) {

        // 자기 자신 follow 안됨
        if (follower == following)
            throw new FollowException(ExceptionCode.FAIL_SELF_FOLLOW_ERROR);

        // 중복 follow x
        if (followRepository.existsByFollowingAndFollower(following , follower)){
            throw new FollowException(ExceptionCode.ALREADY_FOLLOW_ERROR);
        }

        // Follow 엔티티 생성
        Follow follow = Follow.builder()
                .following(following)
                .follower(follower)
                .build();

        // follower , followee follow List 업데이트
        follower.getFollowings().add(follow);
        following.getFollowers().add(follow);

        followRepository.save(follow);

        return "팔로우에 성공했습니다. (상대방에게 메세지 전송)";
    }

    /**
     * unfollow 요청
     * @Param User 언팔로우 요청 유저 , User 언팔로우 받은 유저
     * @Return void
     * */
    @Transactional
    public void cancelFollow(User following , User follower) {
        Optional<Follow> follow = followRepository.deleteByFollowingAndFollower(following, follower);

        if (!follow.isPresent()) {
            throw new FollowException(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "시스템 오류로 인해 언팔로우에 실패했습니다."
            );
        }

        // 언팔 성공 시 follower , followee followList 갱신
        following.getFollowers().remove(follow.get());
        follower.getFollowings().remove(follow.get());
    }

    @Transactional(readOnly = true)
    public Boolean isFollowed(User following , User follower) {

        return followRepository.existsByFollowingAndFollower(following, follower);
    }

}