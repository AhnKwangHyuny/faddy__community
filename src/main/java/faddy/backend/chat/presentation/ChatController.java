package faddy.backend.chat.presentation;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.service.LoadChatMessageService;
import faddy.backend.chat.service.LoadChatRoomService;
import faddy.backend.global.response.ApiResponse;
import faddy.backend.global.response.ErrorApiResponse;
import faddy.backend.global.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final LoadChatRoomService loadChatRoomService;
    private final LoadChatMessageService loadChatMessageService;

    private final String LOCATION = ChatController.class.getName();

    // 특정 room에 전체 chat message 조회
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<? extends ApiResponse> getChatMessages(@PathVariable(value = "roomId") Long roomId) {
        try {
            ChatRoom room = loadChatRoomService.getChatRoomById(roomId);
            List<ChatMessageResponse> messages = loadChatMessageService.loadChatsByChatRoom(room);
            return SuccessApiResponse.of(messages);
        } catch (Exception e) {
            log.error(LOCATION + e.getMessage(), e);
            return ErrorApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
