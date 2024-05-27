package faddy.backend.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ErrorChatSenderDto(String sender) {
}
