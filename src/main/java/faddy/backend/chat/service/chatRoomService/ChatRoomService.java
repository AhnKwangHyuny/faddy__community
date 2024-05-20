package faddy.backend.chat.service.chatRoomService;

import faddy.backend.chat.dto.request.UpdateChatRoomRequest;

public interface ChatRoomService {
    void updateChatRoom(Long roomId, UpdateChatRoomRequest request);
    void deleteChatRoom(Long roomId);


}
