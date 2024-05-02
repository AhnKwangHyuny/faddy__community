package faddy.backend.snap.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = JsonDeserializer.class)
public class ThumbnailResponseDto {

    @NotNull
    private String snapId;

    @NotNull
    private String userId;

    private String profileImageUrl;
    private String description;
    private List<String>  hashTags;

    @JsonProperty(defaultValue = "Unknown")
    private String nickname;
    private String thumbnailImageUrl;


}
