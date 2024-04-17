package faddy.backend.snap.repository;

import faddy.backend.snap.domain.Snap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnapRepository extends JpaRepository<Snap , Long> {
    @Modifying
    @Query("UPDATE Snap s SET s.deleted = true WHERE s.id = ?1")
    void deleteById(Long id);

    Optional<Snap> save(Snap snap);


}
