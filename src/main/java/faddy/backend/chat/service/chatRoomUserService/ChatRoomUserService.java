package faddy.backend.chat.service.chatRoomUserService;

import faddy.backend.chat.domain.ChatRoomUser;

import java.util.List;

public interface ChatRoomUserService {

    ChatRoomUser addUserToChatRoom(Long userId, Long chatRoomId);

    void removeUserFromChatRoom(Long userId, Long chatRoomId);

    List<ChatRoomUser> getUsersByChatRoom(Long chatRoomId);

    List<ChatRoomUser> getChatRoomsByUser(Long userId);
}
