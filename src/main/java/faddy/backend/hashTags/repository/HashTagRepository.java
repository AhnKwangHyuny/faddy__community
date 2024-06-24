package faddy.backend.hashTags.repository;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.repository.custom.CustomHashTagRepository;
import faddy.backend.styleBoard.domain.StyleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long>, CustomHashTagRepository {
    List<HashTag> findByIdIn(List<Long> ids);

    // styleBoardId에 해당하는 해쉬태그 리스트를 반환
    @Query("SELECT h FROM HashTag h WHERE h.styleBoard.id = :styleBoardId")
    List<HashTag> findByStyleBoardId(@Param("styleBoardId")Long styleBoardId);

    @Query("SELECT ht.styleBoard FROM HashTag ht WHERE ht.name IN :tags GROUP BY ht.styleBoard HAVING COUNT(ht.id) = :tagSize")
    List<StyleBoard> findStyleBoardsByTags(@Param("tags") List<String> tags, @Param("tagSize") long tagSize);
}
