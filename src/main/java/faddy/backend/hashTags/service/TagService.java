package faddy.backend.hashTags.service;

import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagResponseDto;

import java.util.List;

public interface TagService {
    List<HashTagResponseDto> saveTags(HashTagRequestDto tagRequest);
}
