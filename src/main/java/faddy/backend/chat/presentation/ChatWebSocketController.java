package faddy.backend.chat.presentation;

import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.command.ChatMessageCreateCommand;
import faddy.backend.chat.dto.request.AuthorizationTokenRequest;
import faddy.backend.chat.dto.request.ChatMessageRequest;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.service.ChatMessageCreateService;
import faddy.backend.chat.service.ChatRoomSystemMessageService;
import faddy.backend.chat.service.ChatRoomValidationService;
import faddy.backend.chat.service.LoadChatRoomService;
import faddy.backend.chat.type.ContentType;
import faddy.backend.global.exception.AuthorizationException;
import faddy.backend.user.service.UserIdEncryptionUtil;
import faddy.backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatWebSocketController {

    private final LoadChatRoomService loadChatRoomService;
    private final ChatMessageCreateService chatMessageCreateService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final SimpMessageSendingOperations messagingTemplate;
    private final UserIdEncryptionUtil userIdEncryptionUtil;
    private final ChatRoomValidationService chatRoomValidationService;
    private final ChatRoomSystemMessageService chatRoomSystemMessageService;

    private final String LOCATION = ChatWebSocketController.class.getName();

    @MessageMapping("/talks/{roomId}/send")
    @Async
    public void chat(@DestinationVariable("roomId") Long roomId,
                     @RequestBody @Valid ChatMessageRequest chatMessage) {
        try {
            // 토큰에서 "Bearer " 부분을 제거하고 실제 토큰만 추출
            String token = jwtUtil.extractRawToken(chatMessage.token());
            Long sender = userService.getUserIdByAuthorization(token);
            String username = userService.getUsernameByToken(token);

            ChatRoom room = loadChatRoomService.getChatRoomById(roomId);

            ChatMessageCreateCommand command = ChatMessageCreateCommand.builder()
                    .content(chatMessage.content())
                    .sender(sender)
                    .room(room)
                    .type(chatMessage.contentType())
                    .build();

            Chat chat = chatMessageCreateService.createChatMessage(command);

            ChatMessageResponse response = ChatMessageResponse.builder()
                    .id(chat.getId())
                    .content(chatMessage.content())
                    .sender(username) // 유저 계정
                    .type(command.type())
                    .createdAt(chat.getCreated_at())
                    .build();

            messagingTemplate.convertAndSend("/sub/talks/" + roomId, response);

        } catch (Exception e) {
            log.error(e.getMessage());
            ChatMessageResponse errorResponse = chatMessageCreateService.createErrorResponse();
            messagingTemplate.convertAndSend("/sub/talks/" + roomId, errorResponse);
        }
    }

    // 채팅방 입장 시 시스템 메시지 전송
    @MessageMapping("/talks/{roomId}/enter")
    @Async
    public void enterChatRoom(@DestinationVariable("roomId") Long roomId,
                              @RequestBody @Valid AuthorizationTokenRequest authorization) {
        try {
            String token = authorization.token();

            ChatMessageResponse response = chatRoomSystemMessageService.enterChatRoom(roomId, token);
            messagingTemplate.convertAndSend("/sub/talks/" + roomId, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            ChatMessageResponse errorResponse = chatMessageCreateService.createErrorResponse();
            messagingTemplate.convertAndSend("/sub/talks/" + roomId, errorResponse);
        }
    }
}
