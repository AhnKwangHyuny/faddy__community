package faddy.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getCode() , e.getMessage() ));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(final NullPointerException e) {
        log.warn(e.getMessage() , e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        log.warn(ex.getMessage(), ex);

        // 클라이언트에게 오류 메시지를 반환합니다.
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value() , ex.getMessage()));
    }

    // server error
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerException(final InternalServerException e) {
        log.warn(e.getMessage() , e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(final AuthenticationException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    @ExceptionHandler(UnAuthorizationException.class)
    public ResponseEntity<ExceptionResponse> handleUnAuthenticationException(final AuthenticationException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleAuthorizationException(final IllegalArgumentException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(JwtValidationException.class)
    public ResponseEntity<ExceptionResponse> handleJwtValidationException(final JwtValidationException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(RefreshTokenSaveException.class)
    public ResponseEntity<ExceptionResponse> handleJwtValidationException(final RefreshTokenSaveException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(SaveEntityException.class)
    public ResponseEntity<ExceptionResponse> handleSaveEntityErrorException(final SaveEntityException e) {
        log.warn(e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(SnapException.class)
    public ResponseEntity<ExceptionResponse> handleSnapException(final SnapException e) {
        log.error("SnapException occurred", e); // 스택 트레이스를 로그에 출력

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(FollowException.class)
    public ResponseEntity<ExceptionResponse> handleFollowException(final SnapException e) {
        log.error("FollowException occurred", e); // 스택 트레이스를 로그에 출력

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }


}

