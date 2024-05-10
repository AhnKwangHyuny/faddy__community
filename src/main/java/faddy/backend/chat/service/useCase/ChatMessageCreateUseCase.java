package faddy.backend.chat.service.useCase;

import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.response.ChatMessageResponse;

public interface ChatMessageCreateUseCase {
    Long createChatMessage(ChatMessageCreateCommand command);

    ChatMessageResponse createErrorResponse( );
}
