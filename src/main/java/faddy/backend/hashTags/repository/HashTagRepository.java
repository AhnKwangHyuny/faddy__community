package faddy.backend.hashTags.repository;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.repository.custom.CustomHashTagRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long>, CustomHashTagRepository {
    List<HashTag> findByIdIn(List<Long> ids);


//    /**
//     *  해시태그 엔티티 리스트를 (영속화 x) 저장
//     *  @param hashTags 해시태그 엔티티 리스트
//     *  @return List<HashTag> 해시태그 엔티티 리스트
//     * */
//    List<HashTag> saveAll(List<HashTag> hashTags);


}
