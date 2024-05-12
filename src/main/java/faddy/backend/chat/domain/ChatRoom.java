package faddy.backend.chat.domain;

import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name ="chat_rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    private Long masterId;

    private String title;

    // category , hashTag 추후 업데이트

    @Builder
    public ChatRoom(Long masterId, String title) {
        this.masterId = masterId;
        this.title = title;
    }

    public void updateChatRoom(Long masterId, String title) {
        this.masterId = masterId;
        this.title = title;
    }

    public static ChatRoom createChatRoom(String title,  Long master) {
        return ChatRoom.builder()
                .title(title)
                .masterId(master)
                .build();
    }
}
