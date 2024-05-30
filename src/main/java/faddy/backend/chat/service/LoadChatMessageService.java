package faddy.backend.chat.service;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.LastChatContentDto;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.useCase.ChatMessageLoadUseCase;
import faddy.backend.chat.type.ContentType;
import faddy.backend.user.service.UserIdEncryptionUtil;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class LoadChatMessageService implements ChatMessageLoadUseCase {

    private final ChatJpaRepository chatRepository;
    private final ChatRoomJpaRepository chatRoomRepository;
    private final UserIdEncryptionUtil userIdEncryptionUtil;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageResponse> loadChatsByChatRoom(ChatRoom chatRoom) {
        try {
            List<Chat> chats = chatRepository.findByChatRoom(chatRoom);

            return chats.stream()
                    .map(chat -> ChatMessageResponse.builder()
                            .id(chat.getId())
                            .content(chat.getContent())
                            .sender(chat.getType() == ContentType.TIMESTAMP || chat.getType() == ContentType.SYSTEM
                                    ? "system"
                                    : userService.getUsernameByUserId(chat.getSenderId()))
                            .type(chat.getType())
                            .createdAt(chat.getCreated_at())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load chat messages", e);
        }
    }

    /**
     * 채당에서 가장 최근 메시지의 content와 type을 조회
     * @param roomId       chatRoom id)
     * @return LastChatContentDto -> content , type을 포함
     */
    @Override
    @Transactional(readOnly = true)
    public LastChatContentDto loadLastChatMessage(Long roomId) {

        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));
        List<LastChatContentDto> results = chatRepository.findAllByChatRoomOrderByCreatedAtDesc(room);
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
}
