package faddy.backend.chat.dto.request;

import lombok.Builder;

@Builder
public record ChatRoomResponse(Long roomId) {
}
