package faddy.backend.chat.domain;

import faddy.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    private Long masterId;

    private String name;

    // category , hashTag 추후 업데이트

    @Builder
    public ChatRoom(Long masterId, String name) {
        this.masterId = masterId;
        this.name = name;
    }

    public void updateChatRoom(Long masterId, String name) {
        this.masterId = masterId;
        this.name = name;
    }

}
