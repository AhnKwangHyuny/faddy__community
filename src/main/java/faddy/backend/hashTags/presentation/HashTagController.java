package faddy.backend.hashTags.presentation;

import faddy.backend.hashTags.domain.dto.response.HashTagResponseDto;
import faddy.backend.hashTags.domain.dto.request.HashTagRequestDto;
import faddy.backend.hashTags.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/hashTags")
public class HashTagController {

    private final TagService tagService;

    @Autowired
    public HashTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation(value = "다중 태그 입력 시 태그 list를 받아와 서버에 저장", notes = "저장 후 tag id list를 클라이언트에 반환")
    @PostMapping()
    public ResponseEntity<List<HashTagResponseDto>> saveTags(@RequestBody HashTagRequestDto tagRequest) {
        List<HashTagResponseDto> savedTags = tagService.saveTags(tagRequest);
        return ResponseEntity.ok(savedTags);
    }
}
