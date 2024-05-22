package faddy.backend.chat.service.adapter;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.CreateChatRoomRequest;
import faddy.backend.chat.dto.response.CreateChatRoomResponse;
import faddy.backend.chat.service.ChatRoomCreateService;
import faddy.backend.chat.service.chatRoomUserService.ChatRoomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomCreatePersistenceAdapter {

    private final ChatRoomCreateService chatRoomCreateService;
    private final ChatRoomUserService chatRoomUserService;

    public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request) {
        ChatRoom room = chatRoomCreateService.createChatRoom(request);
        chatRoomUserService.addUserToChatRoom(room, request.getMemberIds());

        return CreateChatRoomResponse.builder().roomId(room.getId()).build();
    }
}
