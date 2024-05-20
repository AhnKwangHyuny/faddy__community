package faddy.backend.chat.dto.request;

import java.util.List;

public record UserChatRoomRequest(List<String> userIds) {
}
