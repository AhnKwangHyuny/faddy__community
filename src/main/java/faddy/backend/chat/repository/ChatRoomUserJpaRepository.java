package faddy.backend.chat.repository;

import faddy.backend.chat.domain.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomUserJpaRepository extends JpaRepository<ChatRoomUser, Long> {
    List<ChatRoomUser> findByUserId(Long userId);
    List<ChatRoomUser> findByChatRoomId(Long chatRoomId);

    Optional<ChatRoomUser> findByUserIdAndChatRoomId(Long userId, Long chatRoomId);
}
