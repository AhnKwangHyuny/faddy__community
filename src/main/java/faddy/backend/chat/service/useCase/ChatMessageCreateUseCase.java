package faddy.backend.chat.service.useCase;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.response.ChatMessageResponse;

public interface ChatMessageCreateUseCase {
    Chat createChatMessage(ChatMessageCreateCommand command);

    ChatMessageResponse createErrorResponse( );
}
