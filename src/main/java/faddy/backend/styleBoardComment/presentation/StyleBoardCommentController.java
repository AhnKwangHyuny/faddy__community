package faddy.backend.styleBoardComment.presentation;

import faddy.backend.global.response.ApiResponse;
import faddy.backend.global.response.SuccessApiResponse;
import faddy.backend.styleBoardComment.dto.request.StyleBoardCommentCreateRequestDTO;
import faddy.backend.styleBoardComment.dto.response.StyleBoardCommentCreateResponseDTO;
import faddy.backend.styleBoardComment.service.useCase.CreateStyleBoardCommentCreateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/styleBoards")
public class StyleBoardCommentController {

    private final CreateStyleBoardCommentCreateService styleBoardCommentCreateService;
    private final static String CREATE_SUCCESS_MESSAGE = "[create] 댓글이 성공적으로 등록되었습니다.";

    @Description("스타일보드 댓글 생성")
    @PostMapping("/detail/{styleBoardId}/comments")
    public ResponseEntity<? extends ApiResponse> createStyleBoardComment(HttpServletRequest request,
                                                                         @PathVariable("styleBoardId") Long styleBoardId,
                                                                         @RequestBody StyleBoardCommentCreateRequestDTO requestDto) {
        // 댓글 생성
        StyleBoardCommentCreateResponseDTO response = styleBoardCommentCreateService.create(requestDto, request, styleBoardId);

        return SuccessApiResponse.of(HttpStatus.CREATED, CREATE_SUCCESS_MESSAGE, response);
    }



}
