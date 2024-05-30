package faddy.backend.chat.service.chatRoomService;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.UpdateChatRoomRequest;
import faddy.backend.chat.dto.response.ChatRoomResponse;

public interface ChatRoomService {
    void updateChatRoom(Long roomId, UpdateChatRoomRequest request);
    void deleteChatRoom(Long roomId);

    ChatRoomResponse mapToChatRoomResponse(ChatRoom room);

}
