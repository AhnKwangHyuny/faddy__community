package faddy.backend.styleBoard.presentation;

import faddy.backend.global.response.ApiResponse;
import faddy.backend.global.response.ErrorApiResponse;
import faddy.backend.global.response.SuccessApiResponse;
import faddy.backend.styleBoard.dto.request.StyleBoardRequestDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardCreateResponseDTO;
import faddy.backend.styleBoard.service.adapter.useCase.StyleBoardCreatePersistenceAdaptor;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/v1/styleBoards")
public class StyleBoardController {

    private final StyleBoardCreatePersistenceAdaptor styleBoardCreatePersistenceAdaptor;
    private static final String CREATE_SUCCESS_MESSAGE = "[create] 게시글이 성공적으로 등록되었습니다.";

    @PostMapping("/create")
    public ResponseEntity<? extends ApiResponse> createStyleBoard(@RequestBody StyleBoardRequestDTO styleBoardRequestDTO,
                                                                  HttpServletRequest request) {
        try {
            Long styleBoardId = styleBoardCreatePersistenceAdaptor.create(styleBoardRequestDTO, request);
            StyleBoardCreateResponseDTO response = new StyleBoardCreateResponseDTO(styleBoardId);

            return SuccessApiResponse.of(HttpStatus.CREATED , CREATE_SUCCESS_MESSAGE , response);
        } catch (Exception e) {
            return ErrorApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
