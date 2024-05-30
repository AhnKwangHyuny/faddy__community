package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatRoomResponse;

import java.util.List;

public interface ChatRoomLoadUseCase {
    ChatRoom getChatRoomById(Long roomId);

    List<ChatRoomResponse> getChatRooms(int page);

}
