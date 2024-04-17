package faddy.backend.hashTags.service;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagIdResponseDto;

import java.util.List;

public interface TagService {
    List<HashTagIdResponseDto> saveTags(HashTagRequestDto request);

    List<HashTag> findHashTagsByIds(List<Long> ids);

    }
