package faddy.backend.chat.dto.request;

import faddy.backend.chat.type.ContentType;
import lombok.Builder;

@Builder
public record ChatMessageRequest( String content , ContentType contentType){

}