package faddy.backend.chat.dto.response;


import lombok.Builder;

import java.util.List;

@Builder
public record ChatRoomResponse(Long roomId ,String title , List<ChatMessageResponse> messageList) {
}
