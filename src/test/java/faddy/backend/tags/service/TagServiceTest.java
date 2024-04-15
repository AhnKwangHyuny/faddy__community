package faddy.backend.tags.service;

import faddy.backend.tag.domain.Tag;
import faddy.backend.tag.domain.dto.request.TagRequestDto;
import faddy.backend.tag.domain.dto.response.TagResponseDto;
import faddy.backend.tag.repository.TagRepository;
import faddy.backend.tag.service.TagService;
import faddy.backend.type.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void saveTagsTest() {
        // Given
        List<String> tagNames = Arrays.asList("tag1", "tag2");
        TagRequestDto tagRequest = new TagRequestDto(ContentType.SNAP, tagNames);
        List<Tag> tags = Arrays.asList(new Tag("tag1", ContentType.SNAP), new Tag("tag2", ContentType.SNAP));


        // When
        List<TagResponseDto> savedTags = tagService.saveTags(tagRequest);

        // Then
        assertEquals(2, savedTags.size());
        assertEquals("tag1", savedTags.get(0).getName());
        assertEquals("tag2", savedTags.get(1).getName());
    }
}
