package faddy.backend.chat.service.adapter.useCase;

import faddy.backend.chat.dto.response.ChatRoomResponse;

public interface LoadChatRoomLoader {
    ChatRoomResponse loadChatRoom(Long RoomId);
}
