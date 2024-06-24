package faddy.backend.styleBoard.repository;

import faddy.backend.styleBoard.domain.StyleBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StyleBoardJpaRepository extends JpaRepository<StyleBoard, Long> {

    Optional<StyleBoard> findById(Long id);

    @EntityGraph(attributePaths = {"author", "author.profile"})
    @Query("SELECT sb FROM StyleBoard sb WHERE sb.id = :styleBoardId")
    Optional<StyleBoard> findByIdWithAuthorAndProfile(@Param("styleBoardId") Long styleBoardId);

    @Query("SELECT CASE " +
            "WHEN COUNT(sb) > 0 " +
            "THEN TRUE ELSE FALSE " +
            "END FROM StyleBoard sb " +
            "WHERE sb.id = :styleBoardId AND sb.author.id = :userId")
    boolean existsByIdAndUserId(@Param("styleBoardId") Long styleBoardId, @Param("userId") Long userId);

    @Query("SELECT sb FROM StyleBoard sb")
    List<StyleBoard> findAll();

}
