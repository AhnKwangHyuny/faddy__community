package faddy.backend.tag.domain.dto.request;

import faddy.backend.type.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TagRequestDto {
    private ContentType contentType;
    private List<String> list;
}
