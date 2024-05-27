package faddy.backend.chat.service;


import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.ErrorChatSenderDto;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.useCase.ChatMessageCreateUseCase;
import faddy.backend.chat.type.ContentType;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.InternalServerException;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageCreateService implements ChatMessageCreateUseCase {

    private static final String ERROR_MESSAGE = "메세지 전송 중 오류가 발생했습니다.";

    private final UserService userService;
    private final LoadChatRoomService loadChatRoomService;
    private final ChatJpaRepository chatRepository;
    private final ChatRoomJpaRepository chatRomanRepository;
    @Override
    @Transactional
    public Chat createChatMessage(ChatMessageCreateCommand command) {

        //Chat entity 생성
        Chat chat = Chat.createChat(command.room() , command.content(), command.sender() , command.type());

        //chat 저장 및 chat id 반환
        try {
            return chatRepository.save(chat);

        } catch (Exception e) {
            throw new InternalServerException(ExceptionCode.CHAT_SAVE_ERROR);
        }

    }

    @Override
    public List<Chat> createChatMessagesWithTimestamp(ChatMessageCreateCommand command) {

        ChatRoom room = command.room();

        LocalDate currentChatDate = LocalDate.now();
        LocalDate lastChatDate = room.getLastChatTime() != null ? room.getLastChatTime().toLocalDate() : currentChatDate;

        boolean isNewDay = lastChatDate == null || !lastChatDate.equals(currentChatDate);

        List<Chat> result = new ArrayList<>();
        if (isNewDay) {
            String dateStamp = currentChatDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일 E요일", Locale.KOREAN));
            Chat timestampChat = Chat.createTimeStamp(room, dateStamp);

            chatRepository.save(timestampChat);
            result.add(timestampChat);
        }

        Chat newChat = createChatMessage(command);

        //room의 lastChatTime 업데이트
        room.updateLastChatTime(newChat.getCreated_at());

        //변경 사항 저장 (dirty checking 안되는 이슈 해결을 위한 임시방편)
        chatRomanRepository.save(room);

        chatRepository.save(newChat);
        result.add(newChat);

        return result;
    }



    @Override
    public ChatMessageResponse createErrorResponse(ErrorChatSenderDto errorChatSenderDto){
        return ChatMessageResponse.builder()
                .id(-1L) // 에러 시 음수 값 할당
                .content(ERROR_MESSAGE)
                .sender(errorChatSenderDto.sender()) // 에러 시 음수 값 할당
                .type(ContentType.ERROR)
                .build();
    }


}
