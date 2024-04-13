package faddy.backend.auth.presentation;

import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.email.service.MailService;
import faddy.backend.global.Utils.RedisUtil;
import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.api.response.AuthCodeVerificationResult;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.ServerProcessingException;
import faddy.backend.user.dto.request.AuthCodeAndEmailDto;
import faddy.backend.user.dto.request.EmailRequestDto;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-codes")
public class AuthCodeController {

    private final MailService mailService;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;

    private final String BEARER = "Bearer ";
    private final String AUTHENTICATION = "Authentication" ;

    private final long SIGNUP_TOKEN_EXPIRE_TIME = 30 * 60 * 1000; // 30분


    @ApiOperation(value = "인증 코드 검증", notes = "이 API 엔드포인트는 사용자가 제공한 이메일 인증 코드를 검증 후 유효하면 인증토큰을 생성해 딜리버리 한다.")
    @PostMapping("/verify")
    public ResponseEntity verifyEmailAuthCode(@RequestBody @Valid AuthCodeAndEmailDto request , HttpServletResponse response) throws NoSuchAlgorithmException {

        String email = request.getEmail();
        String code = request.getCode();
        String token = null;

        AuthCodeVerificationResult result = mailService.verifiedCode(email, code);

        try {
            if(result.getResult()) {

                // jwt 인증 토큰 생성
                final long now = System.currentTimeMillis();
                Date expiredAt = new Date(now + SIGNUP_TOKEN_EXPIRE_TIME);

                token = jwtUtil.generate(email, expiredAt);

            }

        } catch (Exception e) {
            throw new ServerProcessingException(ExceptionCode.TOKEN_GENERATION_ERROR);
        }
        
        if(token == null) {
            throw new ServerProcessingException(ExceptionCode.TOKEN_GENERATION_ERROR);
        }
        
        // responseDto 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHENTICATION , BEARER + token);

        Map<String, String> data = new HashMap<>();
        data.put("authentication", BEARER + token);


        return ResponseEntity.status(200)
                .headers(headers)
                .body(ResponseDto.response(
                   "200",
                   "인증토큰 발급이 완료되었습니다.",
                    data
                    )
                );
    }

    /**
     * 이메일 인증 코드를 발송하는 메소드.
     * @param emailDto 이메일 주소를 포함하는 EmailRequestDto 객체.
     * @return 인증 코드 발송 결과를 HTTP 상태 코드로 클라이언트에 반환
     */

    @ApiOperation(value = "인증 코드 발송", notes = "이 API 엔드포인트는 클라이언트가 요청한 이메일을 검증하고 인증 코드를 발송한다.")
    @PostMapping
    public ResponseEntity sendMessage(@RequestBody EmailRequestDto emailDto) throws NoSuchAlgorithmException {


        String email = emailDto.getEmail();

        if(email.isEmpty() || !mailService.isValidEmail(email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        mailService.sendCodeToMail(email); // 이메일 유효성 , 중복 검사 후 인증코드 발송
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "인증 코드 삭제" , notes = "인증 시간 만료 시 db에 저장된 authcode 삭제")
    @DeleteMapping
    public ResponseEntity deleteAuthCode(@RequestBody EmailRequestDto emailDto) {

        log.info("emailDto : " + emailDto.getEmail());

        String email = emailDto.getEmail();

        String key = mailService.createKey(email);

        if(email.isEmpty() || !mailService.isValidEmail(email) || !redisUtil.hasKey(key) )  {

            return ResponseEntity.badRequest().body(
                    ResponseDto.response(
                            "400",
                            "유효한 이메일이 아닙니다. 확인 후 재 요청 부탁드립니다."
                    )
            );
        }

        redisUtil.deleteData(key);

        return ResponseEntity.status(200)
            .body(ResponseDto.response(
                    "200",
                    "정상적으로 삭제되었습니다."
            ));
    }
}
