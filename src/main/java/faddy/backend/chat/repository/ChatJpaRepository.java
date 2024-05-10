package faddy.backend.chat.repository;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatJpaRepository extends JpaRepository<Chat , Long> {

    List<Chat> findByChatRoom(ChatRoom chatRoom);

}
