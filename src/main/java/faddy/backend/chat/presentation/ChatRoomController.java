package faddy.backend.chat.presentation;


import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.service.LoadChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/rooms")
public class ChatRoomController {

    private final LoadChatRoomService loadChatRoomService;
    @GetMapping
    public ResponseEntity<ResponseEntity<List<ChatRoom>>> getRoomList() {

        List<ChatRoom> rooms = loadChatRoomService.getChatRooms();

        return null;

    }



}
