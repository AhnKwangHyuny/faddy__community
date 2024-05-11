package faddy.backend.chat.service.adapter;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.dto.response.ChatRoomResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.LoadChatMessageService;
import faddy.backend.chat.service.adapter.useCase.LoadChatRoomLoader;
import faddy.backend.global.annotation.adapter.PersistenceAdapter;
import faddy.backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomLoadPersistenceAdapter implements LoadChatRoomLoader {
    private static final String LOG_LOCATION = ChatRoomLoadPersistenceAdapter.class.getName();

    private final ChatJpaRepository chatRepository;
    private final ChatRoomJpaRepository chatRoomRepository;
    private final LoadChatMessageService loadChatMessageService;

    @Override
    public ChatRoomResponse loadChatRoomResponse(Long roomId) {

        // chatRoom entity 불러오기
        Optional<ChatRoom> roomOpt = chatRoomRepository.findById(roomId);

        roomOpt.orElseThrow(() ->
                new BadRequestException(HttpStatus.BAD_REQUEST.value(),
                        LOG_LOCATION + " [loadChatRoom] 존재하지 않는 roomId 입니다.")
        );

        ChatRoom room = roomOpt.get();

        List<ChatMessageResponse> chats = loadChatMessageService.loadChatsByChatRoom(room);

        return ChatRoomResponse.builder()
                .title(room.getTitle())
                .messageList(chats)
                .roomId(room.getId())
                .build();
    }
}