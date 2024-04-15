package faddy.backend.tag.presentation;

import faddy.backend.tag.domain.dto.response.TagResponseDto;
import faddy.backend.tag.domain.dto.request.TagRequestDto;
import faddy.backend.tag.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation(value = "다중 태그 입력 시 태그 list를 받아와 서버에 저장", notes = "저장 후 tag id list를 클라이언트에 반환")
    @PostMapping()
    public ResponseEntity<List<TagResponseDto>> saveTags(@RequestBody TagRequestDto tagRequest) {
        List<TagResponseDto> savedTags = tagService.saveTags(tagRequest);
        return ResponseEntity.ok(savedTags);
    }
}
