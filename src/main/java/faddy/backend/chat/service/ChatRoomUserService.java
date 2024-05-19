package faddy.backend.chat.service;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.domain.ChatRoomUser;
import faddy.backend.chat.repository.ChatRoomUserJpaRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomUserService {

    private final UserService userService;
    private final ChatRoomUserJpaRepository chatRoomUserRepository;

    public void addUsersToChatRoom(ChatRoom room, List<String> memberIds) {
        List<Long> decryptedMemberIds = memberIds.stream()
                .map(userService::decryptUserId)
                .toList();

        for (Long memberId : decryptedMemberIds) {
            User user = userService.findUserById(memberId);
            ChatRoomUser chatRoomUser = new ChatRoomUser(user, room);
            chatRoomUserRepository.save(chatRoomUser);
        }
    }
}