package faddy.backend.follows.repository;

import faddy.backend.follows.domain.Follow;
import faddy.backend.user.domain.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowJpaRepository extends JpaRepository<Follow, Long> {

    // 팔로우 요청한 유저 간 이미 팔로우 상태인지 확인하는 쿼리
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM Follow f " +
            "WHERE f.following = :following AND f.follower = :follower")
    boolean existsByFollowingAndFollower(@Param("following") User following, @Param("follower") User follower);

    // 언팔
    Optional<Follow> deleteByFollowingAndFollower( User following, User follower);

}