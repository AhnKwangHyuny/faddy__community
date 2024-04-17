package faddy.backend.hashTags.repository;

import faddy.backend.hashTags.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<HashTag> findByIdIn(List<Long> ids);
}
