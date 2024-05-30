package faddy.backend.chat.service;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatRoomResponse;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.chatRoomService.ChatRoomService;
import faddy.backend.chat.service.useCase.ChatRoomLoadUseCase;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.snap.domain.Snap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class LoadChatRoomService implements ChatRoomLoadUseCase {

    private final ChatRoomJpaRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatRoom getChatRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new BadRequestException(ExceptionCode.CHATROOM_NOT_FOUND_ERROR));
    }

    @Override
    public List<ChatRoomResponse> getChatRooms(int page) {

        // db에 snap 데이터 요청 per page
        Pageable pageable = PageRequest.of(page, 6);

        List<ChatRoom> chatRooms = chatRoomRepository.findAllTalksByPage(pageable).getContent();
        
        for (ChatRoom chatRoom : chatRooms) {
            System.out.println("chatRoom.toString() = " + chatRoom.toString());
        }
        
        return chatRooms.stream()
                .map(chatRoomService::mapToChatRoomResponse)
                .collect(Collectors.toList());

    }
}
