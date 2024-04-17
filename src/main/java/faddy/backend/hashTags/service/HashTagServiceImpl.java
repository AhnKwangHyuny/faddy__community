package faddy.backend.hashTags.service;

import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagIdResponseDto;
import faddy.backend.hashTags.repository.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HashTagServiceImpl implements TagService {
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
                .map(hashTag -> new HashTagIdResponseDto( hashTag.getHashTagId()))
                .collect(Collectors.toList());
    }


    public List<HashTag> findHashTagsByIds(List<Long> ids) {

        List<HashTag> foundHashTags = hashTagRepository.findByIdIn(ids);
        if (foundHashTags.size() != ids.size()) {
            throw new BadRequestException(400 , "존재하지 않는 태그가 리스트에 있습니다.");
        }
        return foundHashTags;
    }


}
