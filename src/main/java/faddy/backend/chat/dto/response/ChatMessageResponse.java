package faddy.backend.chat.dto.response;

import lombok.Builder;

@Builder
public record ChatMessageResponse(Long id, String content, Long sender) {

}
