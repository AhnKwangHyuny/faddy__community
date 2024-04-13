package faddy.backend.api.auth;


import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.email.dto.EmailDto;
import faddy.backend.email.service.MailService;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ExceptionResponse;
import faddy.backend.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/auths")
public class AuthController {
    private final MailService mailService;

    private final UserService userService;
    @Autowired
    public AuthController(MailService mailService , UserService userService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
        // 이 메서드에서는 로그 메시지를 출력하지 않습니다.

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getCode() , e.getMessage() ));
    }

    @ApiOperation(value = "유저 정보 중복확인 " , notes = "회원가입 시 입력된 유저 정보에 중복 여부 확인 ")
    @PostMapping("/check-duplication/{field}")
    public ResponseEntity<ResponseDto> checkDuplication(@PathVariable("field") @Valid String field, @RequestBody Map<String , String> user) {

        String value = user.get(field);

        // 사용자 아이다가 없거나 null 인경우 http code 400 반환 (request error)
        if(value == null || value.isEmpty()) {

            throw new BadRequestException(ExceptionCode.INVALID_INPUT_DATA);
        }

        if(userService.isUserIdDuplicated(field , value)) {
            throw new BadRequestException(ExceptionCode.DUPLICATED_INPUT_DATA);
        }


        return ResponseEntity.ok().body(
                ResponseDto.response(
                        "200",
                        "사용 가능한 " + field + " 입니다."
                )
        );

    }

    // 이메일 중복 확인 요청
    @PostMapping("/email/duplicates")
    public ResponseEntity<?> checkEmailDuplication(@RequestBody @Valid EmailDto emailDto) {

        String email = emailDto.getEmail();
        Map<String , Object>  response = new HashMap<>();

        // 이메일 중복 확인
        mailService.checkDuplication(email);

        response.put("message" , "사용 가능한 이메일 입니다.");
        response.put("isDuplicated", false);


        return ResponseEntity.ok().body(response);
    }


}
