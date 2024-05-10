package faddy.backend.chat.repository;

import faddy.backend.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatJpaRepository extends JpaRepository<Chat , Long> {
}
