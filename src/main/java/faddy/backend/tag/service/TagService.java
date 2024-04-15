package faddy.backend.tag.service;

import faddy.backend.tag.domain.dto.request.TagRequestDto;
import faddy.backend.tag.domain.dto.response.TagResponseDto;

import java.util.List;

public interface TagService {
    List<TagResponseDto> saveTags(TagRequestDto tagRequest);
}
