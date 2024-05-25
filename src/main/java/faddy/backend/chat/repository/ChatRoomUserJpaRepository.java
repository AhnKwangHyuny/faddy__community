package faddy.backend.chat.repository;

import faddy.backend.chat.domain.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ChatRoomUserJpaRepository extends JpaRepository<ChatRoomUser, Long> {
    List<ChatRoomUser> findByUser_Id(Long userId);
    List<ChatRoomUser> findByChatRoom_Id(Long chatRoomId);

    @Query("delete from ChatRoomUser cu where cu.user.id = :userId and cu.chatRoom.id = :chatRoomId")
    void deleteByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    @Query("delete from ChatRoomUser cu where cu.chatRoom.id = :roomId")
    void deleteChatRoomUsersByChatRoomId(Long roomId);

    Optional<ChatRoomUser> findByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    // room에 user가 존재하는지 확인
    @Query("select count(cu) > 0 from ChatRoomUser cu where cu.user.id = :userId and cu.chatRoom.id = :chatRoomId")
    boolean existsByUserAndChatRoom(@Param("userId") Long userId, @Param("chatRoomId") Long chatRoomId);
}