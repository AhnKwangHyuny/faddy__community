package faddy.backend.chat.dto.response;

import faddy.backend.chat.type.ContentType;
import lombok.Builder;

@Builder
public record ChatMessageResponse(Long id, String content, Long sender , ContentType type) {

}
