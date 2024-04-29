package faddy.backend.snap.presentation;

import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.exception.SnapException;
import faddy.backend.snap.domain.dto.request.SnapRequestDto;
import faddy.backend.snap.domain.dto.response.SnapResponseDto;
import faddy.backend.snap.service.SnapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/snaps")
@Api(tags = "Snap APIs")
public class SnapController {

    private final SnapService service;

    @ApiOperation(value = "Get snap data", notes = "Retrieve snap data from the database and send it to the client")
    @GetMapping("/detail")
    public ResponseEntity<ResponseDto> getSnapData(@RequestParam("snapId") String snapId) {
        System.out.println("snapId = " + snapId);
        try {
            SnapResponseDto snapResponseDto = service.getSnapResponseDtoWithSnapProjection(snapId);

            System.out.println("snapResponseDto.toString() = " + snapResponseDto.toString());

            return ResponseEntity.ok(createSuccessResponse(snapResponseDto));
        } catch (Exception e) {

            return createErrorResponse(e);
        }
    }

    private <T> ResponseDto<T> createSuccessResponse(T data) {
        return ResponseDto.success(data);
    }

    private ResponseEntity<ResponseDto> createErrorResponse(Exception e) {

        if (e instanceof SnapException) {
            SnapException snapException = (SnapException) e;

            logError(snapException);

            return ResponseEntity.status(snapException.getErrorCode())
                .body(new ResponseDto(
                        String.valueOf(snapException.getErrorCode()),
                        snapException.getMessage()
                )
            );
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(
                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
        "An error occurred while processing the server."
                    )
                );
        }
    }

    private void logError(SnapException e) {
        log.warn("An error occurred while creating the snap response.", e);
    }


}
