package faddy.backend.hashTags.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HashTagResponseDto {
    private String name;
    private Long id;
}
