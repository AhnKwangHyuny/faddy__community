package faddy.backend.chat.service;


import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.service.useCase.ChatMessageCreateUseCase;
import faddy.backend.chat.type.ContentType;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.InternalServerException;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageCreateService implements ChatMessageCreateUseCase {

    private static final String ERROR_MESSAGE = "메세지 전송 중 오류가 발생했습니다.";

    private final UserService userService;
    private final LoadChatRoomService loadChatRoomService;
    private final ChatJpaRepository chatRepository;
    @Override
    public Long createChatMessage(ChatMessageCreateCommand command) {


        //Chat entity 생성
        Chat chat = Chat.createChat(command.room() , command.content(), command.sender() , command.type());

        //chat 저장 및 chat id 반환
        try {
            Chat savedChat = chatRepository.save(chat);

            return savedChat.getId();

        } catch (Exception e) {
            throw new InternalServerException(ExceptionCode.CHAT_SAVE_ERROR);
        }

    }

    @Override
    public ChatMessageResponse createErrorResponse() {
        return ChatMessageResponse.builder()
                .id(-1L) // 에러 시 음수 값 할당
                .content(ERROR_MESSAGE)
                .sender(-1L) // 에러 시 음수 값 할당
                .type(ContentType.TEXT)
                .build();
    }
}
