package faddy.backend.snap.presentation;

import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.request.CreateSnapRequestDto;
import faddy.backend.snap.service.SnapUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
public class SnapController {

    private final SnapUploadService snapUploadService;

    @ApiOperation(value = "Create a new Snap", notes = "Create a new Snap with the provided information")
    @PostMapping
    public ResponseEntity<Snap> createSnap(@RequestBody @Valid CreateSnapRequestDto request) {
        log.info("Received request to create a new Snap: {}", request);

//        Snap createdSnap = snapUploadService.createSnap(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdSnap);
        return null;
    }
}