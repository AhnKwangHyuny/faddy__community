package faddy.backend.chat.service.adapter.useCase;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.CreateChatRoomRequest;
import faddy.backend.chat.dto.response.CreateChatRoomResponse;

public interface CreateChatRoomUseCase {

    ChatRoom createChatRoom(CreateChatRoomRequest request);
}
