package faddy.backend.chat.domain;

import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

    private String message;

    //image url 추후 추가

    @Builder
    public Chat(ChatRoom chatRoom, Long senderId, String message) {
        this.chatRoom =chatRoom;
        this.senderId = senderId;
        this.message = message;
    }

}
