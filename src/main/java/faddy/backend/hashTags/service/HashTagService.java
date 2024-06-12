package faddy.backend.hashTags.service;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagIdResponseDto;
import faddy.backend.hashTags.dto.request.HashTagRequestDTO;

import java.util.List;

public interface HashTagService {
    List<HashTagIdResponseDto> saveTags(HashTagRequestDto request);

    /**
     * 해쉬태그 리스트를 전달 받아 모두 엔티티로 매핑 후 엔티티 저장
     * @param hashTagRequestDTOs 해쉬태그 리스트
     * @return List<HashTag> 해쉬태그 엔티티 리스트
     * */
    List<HashTag> saveHashTags(List<HashTagRequestDTO> hashTagRequestDTOs);

    List<HashTag> findHashTagsByIds(List<Long> ids);

    }
