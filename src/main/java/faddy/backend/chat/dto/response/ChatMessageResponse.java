package faddy.backend.chat.dto.response;

import faddy.backend.chat.type.ContentType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChatMessageResponse(Long id, String content, String sender , ContentType type , LocalDateTime createdAt) {

}
