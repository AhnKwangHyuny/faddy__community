package faddy.backend.image.repository;

import faddy.backend.image.domain.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    Optional<Image> findByHashName(String hashName);

    @Transactional
    @Modifying
    @Query("DELETE FROM Image i WHERE i.hashName IN :hashNames")
    void deleteAllByHashNameIn(@Param("hashNames") List<String> hashNames);

    @Query(value = "SELECT * FROM images", nativeQuery = true)
    List<Image> findAll();

}
