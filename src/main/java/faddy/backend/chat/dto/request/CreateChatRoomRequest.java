package faddy.backend.chat.dto.request;


import faddy.backend.chat.type.ChatRoomType;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateChatRoomRequest {

    private ChatRoomType type;

    private String masterId;

    private List<String> memberIds;

}
