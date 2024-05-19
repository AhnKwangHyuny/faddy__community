package faddy.backend.chat.domain;

import faddy.backend.chat.type.ChatRoomType;
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

    @Enumerated(EnumType.STRING)
    private ChatRoomType type;

    // category , hashTag 추후 업데이트

    @Builder
    public ChatRoom(Long masterId, ChatRoomType type) {
        this.masterId = masterId;
        this.type = type;

        this.title = null;
    }

    public void updateChatRoom(Long masterId, String title) {
        this.masterId = masterId;
        this.title = title;
    }

    // create builder using type and masterId except title
    public static ChatRoom createChatRoom(Long masterId, ChatRoomType type) {
        return ChatRoom.builder()
                .masterId(masterId)
                .type(type)
                .build();
    }

}
