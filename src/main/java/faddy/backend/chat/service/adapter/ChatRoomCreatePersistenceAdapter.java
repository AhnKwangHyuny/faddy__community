package faddy.backend.chat.service.adapter;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.CreateChatRoomRequest;
import faddy.backend.chat.dto.response.CreateChatRoomResponse;
import faddy.backend.chat.service.ChatRoomCreateService;
import faddy.backend.chat.service.ChatRoomUserService;
import faddy.backend.chat.service.adapter.useCase.CreateChatRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomCreatePersistenceAdapter implements CreateChatRoomUseCase {

    private final ChatRoomCreateService chatRoomCreateService;
    private final ChatRoomUserService chatRoomUserService;

    @Override
    public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request) {
        ChatRoom room = chatRoomCreateService.createChatRoom(request);
        chatRoomUserService.addUsersToChatRoom(room, request.getMemberIds());

        return CreateChatRoomResponse.builder().roomId(room.getId()).build();
    }
}
