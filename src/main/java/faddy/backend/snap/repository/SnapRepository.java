package faddy.backend.snap.repository;

import faddy.backend.snap.domain.Snap;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnapRepository extends JpaRepository<Snap, Long> {
    // 논리적 삭제된 스냅 제외하고 조회
    Optional<Snap> findByIdAndDeletedAtIsNull(Long snapId);

    // snapId가 존재하며 논리적으로 삭제되지 않은 snap의 id 존재 여부를 확인
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM Snap s " +
            "WHERE s.id = :snapId " +
            "AND s.deletedAt IS NULL")
    boolean existsByIdAndDeletedAtIsNull(@Param("snapId") Long snapId);

    // 특정 ID를 기준으로 Snap 엔티티를 찾으며, user, snapImages, hashTags 필드를 EAGER 로딩으로 가져옵니다.
    @EntityGraph(attributePaths = {"user", "user.profile", "snapImages", "hashTags"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Snap> findById(Long id);
}