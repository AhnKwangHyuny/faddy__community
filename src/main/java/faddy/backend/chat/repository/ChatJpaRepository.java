package faddy.backend.chat.repository;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.type.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatJpaRepository extends JpaRepository<Chat , Long> {

    List<Chat> findByChatRoom(ChatRoom chatRoom);


    /**
     * 해당 채팅방에서 유저가 보낸 가장 최근 메시지를 조회
     * 메시지 유형은 allowedTypes에 포함된 것들만 대상이 됨.
     *
     * @param chatRoom 대상 채팅방
     * @param allowedTypes 조회할 메시지 유형 목록
     * @return 조건에 맞는 가장 최근 메시지 (없을 경우 Optional.empty())
     */
    @Query("SELECT c FROM Chat c WHERE c.chatRoom = :chatRoom AND c.type IN :allowedTypes ORDER BY c.created_at DESC")
    Optional<Chat> findTopByChatRoomOrderByCreatedAtDesc(ChatRoom chatRoom, List<ContentType> allowedTypes);
}