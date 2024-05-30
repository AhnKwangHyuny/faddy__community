package faddy.backend.chat.dto;

import faddy.backend.chat.type.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LastChatContentDto {
    String content;
    ContentType type;
}
