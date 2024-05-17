package faddy.backend.chat.domain;

import faddy.backend.chat.type.ContentType;
import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // join issue 방지 (entity 직접 매핑 x)
    private Long senderId;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContentType type;

    //image url 추후 추가

    @Builder
    public Chat(ChatRoom chatRoom, Long senderId, String content , ContentType type) {
        this.chatRoom =chatRoom;
        this.senderId = senderId;
        this.content = content;
        this.type = type;
    }

    /**
     * 채팅 생성
     * @param room 채팅 방
     * @param sender 보낸이
     * @param content 내용
     * @Param type 타입
     * @return Chat Entity
     */
    public static Chat createChat(ChatRoom room , String content , Long sender , ContentType type) {
        return Chat.builder()
                .chatRoom(room)
                .senderId(sender)
                .content(content)
                .type(type)
                .build();
    }
}
