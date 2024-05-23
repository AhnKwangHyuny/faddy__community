package faddy.backend.chat.dto.request;

import faddy.backend.chat.type.ContentType;
import lombok.Builder;
import lombok.Getter;

@Builder
public record ChatMessageRequest( String content , ContentType contentType , String token){

}