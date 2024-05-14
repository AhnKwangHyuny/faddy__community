package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomLoadUseCase {
    ChatRoom getChatRoomById(Long roomId);

    List<ChatRoom> loadAllChatRooms();

}
