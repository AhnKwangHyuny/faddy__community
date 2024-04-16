package faddy.backend.hashTags.service;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagResponseDto;
import faddy.backend.hashTags.repository.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HashTagServiceImpl implements TagService {
    private final HashTagRepository hashTagRepository;

    @Autowired
    public HashTagServiceImpl(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @Override
    @Transactional
    public List<HashTagResponseDto> saveTags(HashTagRequestDto request) {
        List<HashTag> hashTags = request.getTags().stream()
                .map(tagName -> new HashTag(tagName, request.getContentType()))
                .collect(Collectors.toList());

        List<HashTag> savedTags;

        try {
            savedTags = hashTagRepository.saveAll(hashTags);
        } catch (Exception e) {
            throw new SaveEntityException(ExceptionCode.FAIL_SAVE_ENTITY);
        }

        return savedTags.stream()
                .map(hashTag -> new HashTagResponseDto( hashTag.getHashTagId()))
                .collect(Collectors.toList());
    }

}
