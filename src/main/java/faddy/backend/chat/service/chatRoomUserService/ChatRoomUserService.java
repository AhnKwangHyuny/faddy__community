package faddy.backend.chat.service.chatRoomUserService;

import faddy.backend.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomUserService {

    void addUserToChatRoom(ChatRoom room, List<Long> memberIds);
    void removeUserFromChatRoom(String userId, Long roomId);
    void inviteUserToChatRoom(List<String> users, Long roomId);

}
