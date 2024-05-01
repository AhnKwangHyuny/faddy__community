package faddy.backend.like.repository;

import faddy.backend.like.domain.Like;
import faddy.backend.snap.domain.Snap;
import faddy.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like, Long> {

    List<Like> findBySnap(Snap snap);

    // 특정 Snap에 대한 좋아요 개수 조회
    @Query("SELECT COUNT(l) FROM Like l WHERE l.snap = :snap")
    Long countBySnap(@Param("snap") Snap snap);

    // 특정 User와 Snap에 대한 Like 엔티티 조회
    @Query("SELECT l FROM Like l WHERE l.user = :user AND l.snap = :snap")
    Optional<Like> findByUserAndSnap(@Param("user") User user, @Param("snap") Snap snap);

    // 특정 User가 좋아요한 Snap 목록 조회
    @Query("SELECT l.snap FROM Like l WHERE l.user = :user")
    List<Snap> findSnapsByUser(@Param("user") User user);

    // 특정 Snap에 좋아요한 User 목록 조회
    @Query("SELECT l.user FROM Like l WHERE l.snap = :snap")
    List<User> findUsersBySnap(@Param("snap") Snap snap);

    //snapId로 해당 likes들 모두 삭제
    @Modifying
    @Query("DELETE FROM Like l WHERE l.snap in :snap")
    void deleteAllLikesBySnap(@Param("snap") Snap snap);
}
