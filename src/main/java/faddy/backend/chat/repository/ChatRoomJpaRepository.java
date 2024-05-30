package faddy.backend.chat.repository;

import faddy.backend.chat.domain.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom , Long> {
    @Query("SELECT cr FROM ChatRoom cr ORDER BY cr.created_at DESC")
    Page<ChatRoom> findAllTalksByPage(Pageable pageable);


}
