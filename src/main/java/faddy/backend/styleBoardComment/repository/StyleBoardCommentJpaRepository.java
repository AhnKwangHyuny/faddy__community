package faddy.backend.styleBoardComment.repository;

import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBoardCommentJpaRepository extends JpaRepository<StyleBoardComment, Long> {
}
