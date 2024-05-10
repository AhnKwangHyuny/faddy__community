package faddy.backend.chat.presentation;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.request.ChatMessageRequest;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.service.ChatMessageCreateService;
import faddy.backend.chat.service.LoadChatRoomService;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final LoadChatRoomService loadChatRoomService;
    private final ChatMessageCreateService chatMessageCreateService;
    private final UserService userService;

    @MessageMapping("/chat/rooms/{roomId}/send") // config destination prefix에 더할 url
    @SendTo("/chat/rooms/{roomId}") // simpleBroker 전달
    public ChatMessageResponse chat(@DestinationVariable Long roomId ,
                                    @RequestHeader("Authorization") String token,
                                    ChatMessageRequest chatMessage) {

        try {
            // 채팅 룸 불러오기
            ChatRoom room = loadChatRoomService.getChatRoomById(roomId);

            // 사용자 id 가져오기
            Long sender = userService.getUserIdByAuthorization(token);

            //chat create command 생성
            ChatMessageCreateCommand command = ChatMessageCreateCommand.builder()
                    .content(chatMessage.content())
                    .sender(sender)
                    .room(room)
                    .type(chatMessage.contentType())
                    .build();

            // chat 생성 후 db에 저장
            Long chatId = chatMessageCreateService.createChatMessage(command);

            // response 생성
            ChatMessageResponse chatMessageResponse = ChatMessageResponse.builder()
                    .id(chatId)
                    .content(chatMessage.content())
                    .sender(sender) // 보안 이슈
                    .type(command.type())
                    .build();

            return chatMessageResponse;
        } catch (Exception e) {
            log.error(e.getMessage()); // 에러 log 출력

            // 에러 메세지 생성
            return chatMessageCreateService.createErrorResponse();
        }


    }

}
