package faddy.backend.styleBoard.repository;

import faddy.backend.styleBoard.domain.StyleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBoardJpaRepository extends JpaRepository<StyleBoard, Long> {

}
