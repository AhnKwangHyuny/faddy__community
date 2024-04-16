package faddy.backend.hashTags.domain.dto.request;

import faddy.backend.type.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HashTagRequestDto {
    private ContentType contentType;
    private List<String> tags;
}
