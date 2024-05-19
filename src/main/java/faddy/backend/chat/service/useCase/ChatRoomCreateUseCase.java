package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.command.ChatRoomCreateCommand;
import faddy.backend.chat.dto.request.CreateChatRoomRequest;

public interface ChatRoomCreateUseCase {
    ChatRoom createChatRoom(CreateChatRoomRequest request );
}
