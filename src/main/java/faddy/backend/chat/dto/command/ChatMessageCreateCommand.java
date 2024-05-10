package faddy.backend.chat.dto.command;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.type.ContentType;
import lombok.Builder;

@Builder
public record ChatMessageCreateCommand(ChatRoom room , String content , Long sender , ContentType type) {

}
