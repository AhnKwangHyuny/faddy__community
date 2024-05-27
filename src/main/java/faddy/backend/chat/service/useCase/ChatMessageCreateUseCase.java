package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.dto.ErrorChatSenderDto;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.response.ChatMessageResponse;

import java.util.List;

public interface ChatMessageCreateUseCase {
    Chat createChatMessage(ChatMessageCreateCommand command);

    List<Chat> createChatMessagesWithTimestamp(ChatMessageCreateCommand command);
    ChatMessageResponse createErrorResponse(ErrorChatSenderDto errorChatSenderDto);
}
