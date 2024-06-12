package faddy.backend.styleBoard.presentation;

import faddy.backend.global.response.ApiResponse;
import faddy.backend.global.response.ErrorApiResponse;
import faddy.backend.global.response.SuccessApiResponse;
import faddy.backend.styleBoard.dto.request.StyleBoardRequestDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardCreateResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;
import faddy.backend.styleBoard.service.adapter.useCase.StyleBoardCreatePersistenceAdaptor;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/styleBoards")
public class StyleBoardController {

    private final StyleBoardCreatePersistenceAdaptor styleBoardCreatePersistenceAdaptor;
    private final StyleBoardDetailService styleBoardDetailService;
    private static final String CREATE_SUCCESS_MESSAGE = "[create] 게시글이 성공적으로 등록되었습니다.";
    private static final String CREATE_FAIL_MESSAGE = "[create] 게시글 등록에 실패했습니다.";

    private static final String DETAIL_SUCCESS_MESSAGE = "[detail] 게시글 상세 조회 성공";

    @PostMapping("/create")
    public ResponseEntity<? extends ApiResponse> createStyleBoard(@RequestBody StyleBoardRequestDTO styleBoardRequestDTO,
                                                                  HttpServletRequest request) {
        try {
            Long styleBoardId = styleBoardCreatePersistenceAdaptor.create(styleBoardRequestDTO, request);
            StyleBoardCreateResponseDTO response = new StyleBoardCreateResponseDTO(styleBoardId);

            return SuccessApiResponse.of(HttpStatus.CREATED , CREATE_SUCCESS_MESSAGE , response);
        } catch (Exception e) {
            return ErrorApiResponse.of(HttpStatus.BAD_REQUEST, CREATE_FAIL_MESSAGE);
        }
    }

    @Description("스타일보드 상세 페이지 데이터 조회")
    @GetMapping("/detail/{styleBoard_id}")
    public ResponseEntity<? extends ApiResponse> getStyleBoardDetail(@PathVariable("styleBoard_id") Long styleBoardId,
                                                                     @RequestParam(value = "category", required = true) String category) {

        StyleBoardDetailResponseDTO response = styleBoardDetailService.getStyleBoardDetailData(styleBoardId, category.toUpperCase());

        return SuccessApiResponse.of(HttpStatus.OK, DETAIL_SUCCESS_MESSAGE, response);
    }
}
