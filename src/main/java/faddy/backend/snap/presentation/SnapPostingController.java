package faddy.backend.snap.presentation;

import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.request.CreateSnapRequestDto;
import faddy.backend.snap.service.SnapPostingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/snaps")
@Api(tags = "Snap APIs")
public class SnapPostingController {

    private final SnapPostingService snapUploadService;

    @ApiOperation(value = "새로운 스냅 포스팅", notes = "포스팅 데이터를 클라이언트에게 받아 만든 snap entity를 db에 저장한다.")
    @PostMapping
    public ResponseEntity<Snap> createSnap(@RequestBody @Valid CreateSnapRequestDto request) {

        // snap생성 후 엔티티 반환
        Snap snap = snapUploadService.createSnap(request);

        return ResponseEntity.ok().body(snap);
    }
}