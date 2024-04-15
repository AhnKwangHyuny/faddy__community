package faddy.backend.tag.repository;

import faddy.backend.tag.domain.Tag;
import faddy.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
