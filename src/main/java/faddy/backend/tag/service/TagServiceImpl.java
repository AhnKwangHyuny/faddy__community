package faddy.backend.tag.service;

import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.tag.domain.Tag;
import faddy.backend.tag.domain.dto.request.TagRequestDto;
import faddy.backend.tag.domain.dto.response.TagResponseDto;
import faddy.backend.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public List<TagResponseDto> saveTags(TagRequestDto tagRequest) {
        List<Tag> tags = tagRequest.getList().stream()
                .map(tagName -> new Tag(tagName, tagRequest.getContentType()))
                .collect(Collectors.toList());

        List<Tag> savedTags;
        try {
            savedTags = tagRepository.saveAll(tags);
        } catch (Exception e) {
            throw new SaveEntityException(ExceptionCode.FAIL_SAVE_ENTITY);
        }

        return savedTags.stream()
                .map(tag -> new TagResponseDto(tag.getName(), tag.getTagId()))
                .collect(Collectors.toList());
    }

}
