package faddy.backend.chat.service.useCase;

import faddy.backend.chat.dto.command.ChatRoomCreateCommand;

public interface ChatRoomCreateUseCase {
    Long createChatRoom(ChatRoomCreateCommand command);
}
