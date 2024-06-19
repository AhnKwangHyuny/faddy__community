package faddy.backend.styleBoard.presentation;

import faddy.backend.global.response.ApiResponse;
import faddy.backend.global.response.ErrorApiResponse;
import faddy.backend.global.response.SuccessApiResponse;
import faddy.backend.styleBoard.dto.request.StyleBoardRequestDTO;
import faddy.backend.styleBoard.dto.response.CheckOwnerResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardCreateResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;
import faddy.backend.styleBoard.service.adapter.useCase.StyleBoardCreatePersistenceAdaptor;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.user.service.UserService;
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
    private final UserService userService;
    private static final String CREATE_SUCCESS_MESSAGE = "[create] 게시글이 성공적으로 등록되었습니다.";
    private static final String CREATE_FAIL_MESSAGE = "[create] 게시글 등록에 실패했습니다.";
    private static final String DETAIL_SUCCESS_MESSAGE = "[detail] 게시글 상세 조회 성공";
    private static final String CHECK_OWNER_SUCCESS_MESSAGE = "[check] 작성자 확인 성공";
    private static final String CHECK_OWNER_FAIL_MESSAGE = "[check] 작성자 확인 실패";

    @Description("스타일보드 생성")
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
                                                                     @RequestParam(value = "category", required = true) String category,
                                                                     HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        Long viewerId = null;
        if(token != null) {
            viewerId = userService.findUserIdByToken(token);
        }

        StyleBoardDetailResponseDTO response = styleBoardDetailService.getStyleBoardDetailData(styleBoardId, category.toUpperCase(), viewerId);

        return SuccessApiResponse.of(HttpStatus.OK, DETAIL_SUCCESS_MESSAGE, response);
    }

    @Description("detail 페이지 접근자가 스타일보드 작성자인지 확인")
    @GetMapping("/detail/{styleBoardId}/check-owner")
    public ResponseEntity<? extends ApiResponse> checkStyleBoardOwner(@PathVariable("styleBoardId") Long styleBoardId,
                                                                     HttpServletRequest request) {
        // token 추출
        String token = request.getHeader("Authorization");

        //token 없으면 에러 반환
        if(token == null) {
            return ErrorApiResponse.of(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다.");
        }

        CheckOwnerResponseDTO response = styleBoardDetailService.checkStyleBoardOwner(styleBoardId, token);

        return SuccessApiResponse.of(HttpStatus.OK, CHECK_OWNER_SUCCESS_MESSAGE, response);
    }
}
