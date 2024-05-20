package faddy.backend.chat.repository;

import faddy.backend.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom , Long> {

    @Query("delete from ChatRoomUser cu where cu.chatRoomId = :roomId")
    void deleteChatRoomUsersByChatRoomId(Long roomId);

}
