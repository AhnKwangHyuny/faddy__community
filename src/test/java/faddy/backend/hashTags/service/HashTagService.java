package faddy.backend.hashTags.service;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.domain.dto.response.HashTagResponseDto;
import faddy.backend.hashTags.repository.HashTagRepository;
import faddy.backend.type.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class HashTagService {

    @Autowired
    private TagService tagService;

    @Autowired
    private HashTagRepository hashTagRepository;

    @Test
    public void saveTagsTest() {
        // Given
        List<String> tagNames = Arrays.asList("tag1", "tag2");
        HashTagRequestDto tagRequest = new HashTagRequestDto(ContentType.SNAP, tagNames);
        List<HashTag> tags = Arrays.asList(new HashTag("tag1", ContentType.SNAP), new HashTag("tag2", ContentType.SNAP));


        // When
        List<HashTagResponseDto> savedTags = tagService.saveTags(tagRequest);

        // Then
        assertEquals(2, savedTags.size());
        assertEquals("tag1", savedTags.get(0).getName());
        assertEquals("tag2", savedTags.get(1).getName());
    }
}
