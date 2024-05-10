package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.ChatRoom;

public interface ChatRoomLoadUseCase {
    ChatRoom getChatRoomById(Long roomId);

}
