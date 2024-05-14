package faddy.backend.chat.service;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.useCase.ChatRoomLoadUseCase;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class LoadChatRoomService implements ChatRoomLoadUseCase {

    private final ChatRoomJpaRepository chatRoomRepository;

    @Override
    public ChatRoom getChatRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new BadRequestException(ExceptionCode.CHATROOM_NOT_FOUND_ERROR));
    }

    @Override
    public List<ChatRoom> loadAllChatRooms() {
        return chatRoomRepository.findAll();

    }
}
