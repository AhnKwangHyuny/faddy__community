package faddy.backend.chat.service.chatRoomUserService;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.domain.ChatRoomUser;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.repository.ChatRoomUserJpaRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomUserServiceImpl implements ChatRoomUserService {

    private final UserRepository userRepository;
    private final ChatRoomJpaRepository chatRoomRepository;
    private final ChatRoomUserJpaRepository chatRoomUserRepository;

    @Transactional
    public ChatRoomUser addUserToChatRoom(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

        ChatRoomUser chatRoomUser = new ChatRoomUser(user, chatRoom);
        return chatRoomUserRepository.save(chatRoomUser);
    }

    @Transactional
    public void removeUserFromChatRoom(Long userId, Long chatRoomId) {
        ChatRoomUser chatRoomUser = chatRoomUserRepository.findByUserIdAndChatRoomId(userId, chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 채팅방에 없습니다."));
        chatRoomUserRepository.delete(chatRoomUser);
    }

    @Transactional(readOnly = true)
    public List<ChatRoomUser> getUsersByChatRoom(Long chatRoomId) {
        return chatRoomUserRepository.findByChatRoomId(chatRoomId);
    }

    @Transactional(readOnly = true)
    public List<ChatRoomUser> getChatRoomsByUser(Long userId) {
        return chatRoomUserRepository.findByUserId(userId);
    }
}
