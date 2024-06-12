package faddy.backend.hashTags.service;

import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagIdResponseDto;
import faddy.backend.hashTags.dto.request.HashTagRequestDTO;
import faddy.backend.hashTags.repository.HashTagRepository;
import faddy.backend.log.exception.ExceptionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HashTagServiceImpl implements HashTagService {
    private final HashTagRepository hashTagRepository;

    @Autowired
    public HashTagServiceImpl(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @Override
    @Transactional
    public List<HashTagIdResponseDto> saveTags(HashTagRequestDto request) {
        
        // request.getTags()로 태그 목록을 가져옴 
        List<String> hashTags = request.getTags();

        List<HashTag> hashTagEntities = IntStream.range(0, hashTags.size())
                .mapToObj(index -> {
                    String tagName = hashTags.get(index);
                    return new HashTag(tagName, index , request.getContentType());
                })
                .collect(Collectors.toList());

        
        List<HashTag> savedTags;
        
        try {
            savedTags = hashTagRepository.saveAll(hashTagEntities);

        } catch (Exception e) {
            e.printStackTrace(); // 예외 메시지 출력
            throw new SaveEntityException(ExceptionCode.FAIL_SAVE_ENTITY);
        }

        return savedTags.stream()
                .map(hashTag -> new HashTagIdResponseDto( hashTag.getId()))
                .collect(Collectors.toList());
    }


    public List<HashTag> findHashTagsByIds(List<Long> ids) {

        List<HashTag> foundHashTags = hashTagRepository.findByIdIn(ids);
        if (foundHashTags.size() != ids.size()) {
            throw new BadRequestException(400 , "존재하지 않는 태그가 리스트에 있습니다.");
        }
        return foundHashTags;
    }

    @Override
    @Transactional
    public List<HashTag> saveHashTags(List<HashTagRequestDTO> hashTagRequestDTOs) {
        if (hashTagRequestDTOs == null || hashTagRequestDTOs.isEmpty()) {
            return Collections.emptyList(); // 태그가 없을 시 빈 리스트 반환
        }

        try {
            List<HashTag> hashTagEntities = hashTagRequestDTOs.stream()
                    .map(hashTagRequestDTO -> new HashTag(
                            hashTagRequestDTO.getName(),
                            hashTagRequestDTO.getPriority(),
                            hashTagRequestDTO.getContentType()))
                    .collect(Collectors.toList());

            Optional<List<HashTag>> hashTags = Optional.ofNullable(hashTagRepository.saveAll(hashTagEntities));

            return hashTags.orElse(Collections.emptyList()); // 저장된 해시태그 리스트 반환

        } catch (Exception e) {
            ExceptionLogger.logException(e);
            throw new SaveEntityException(ExceptionCode.FAIL_CREATE_HASH_TAG);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HashTag> findHashTagsByStyleBoardId(Long styleBoardId) {
        return hashTagRepository.findByStyleBoardId(styleBoardId);
    }
}
