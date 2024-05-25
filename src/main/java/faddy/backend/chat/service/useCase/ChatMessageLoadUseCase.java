package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatMessageResponse;

import java.util.List;
import java.util.Optional;

public interface ChatMessageLoadUseCase {
    List<ChatMessageResponse> loadChatsByChatRoom(ChatRoom room);

    Chat loadLastChatMessage(Long roomId);
}
