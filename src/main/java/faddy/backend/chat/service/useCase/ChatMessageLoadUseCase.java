package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.LastChatContentDto;
import faddy.backend.chat.dto.response.ChatMessageResponse;

import java.util.List;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResponse> loadChatsByChatRoom(ChatRoom room);

    LastChatContentDto loadLastChatMessage(Long roomId);

}
