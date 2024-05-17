package faddy.backend.chat.presentation;

import faddy.backend.chat.domain.ChatRoomUser;
import faddy.backend.chat.service.chatRoomUserService.ChatRoomUserService;
import faddy.backend.global.response.SuccessApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room-users")
public class ChatRoomUserController {
    private final ChatRoomUserService chatRoomUserService;

    public ChatRoomUserController(ChatRoomUserService chatRoomUserService) {
        this.chatRoomUserService = chatRoomUserService;
    }

    /**
     * 채팅방에 사용자를 추가합니다.
     *
     * @param userId     추가할 사용자 ID
     * @param chatRoomId 대상 채팅방 ID
     * @return 추가된 ChatRoomUser 객체
     */
    @PostMapping
    public ResponseEntity<SuccessApiResponse<ChatRoomUser>> addUserToChatRoom(@RequestParam Long userId, @RequestParam Long chatRoomId) {
        ChatRoomUser chatRoomUser = chatRoomUserService.addUserToChatRoom(userId, chatRoomId);
        return SuccessApiResponse.of(chatRoomUser);
    }

    /**
     * 채팅방에서 사용자를 제거합니다.
     *
     * @param userId     제거할 사용자 ID
     * @param chatRoomId 대상 채팅방 ID
     * @return 성공 응답
     */
    @DeleteMapping
    public ResponseEntity<SuccessApiResponse<Void>> removeUserFromChatRoom(@RequestParam Long userId, @RequestParam Long chatRoomId) {
        chatRoomUserService.removeUserFromChatRoom(userId, chatRoomId);
        return SuccessApiResponse.of();
    }

    /**
     * 채팅방에 속한 사용자 목록을 가져옵니다.
     *
     * @param chatRoomId 대상 채팅방 ID
     * @return 채팅방에 속한 사용자 목록
     */
    @GetMapping("/users")
    public ResponseEntity<SuccessApiResponse<List<ChatRoomUser>>> getUsersByChatRoom(@RequestParam Long chatRoomId) {
        List<ChatRoomUser> chatRoomUsers = chatRoomUserService.getUsersByChatRoom(chatRoomId);
        return SuccessApiResponse.of(chatRoomUsers);
    }

    /**
     * 사용자가 속한 채팅방 목록을 가져옵니다.
     *
     * @param userId 대상 사용자 ID
     * @return 사용자가 속한 채팅방 목록
     */
    @GetMapping("/chat-rooms")
    public ResponseEntity<SuccessApiResponse<List<ChatRoomUser>>> getChatRoomsByUser(@RequestParam Long userId) {
        List<ChatRoomUser> chatRoomUsers = chatRoomUserService.getChatRoomsByUser(userId);
        return SuccessApiResponse.of(chatRoomUsers);
    }
}