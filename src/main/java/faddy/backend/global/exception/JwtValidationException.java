package faddy.backend.global.exception;

import lombok.Getter;

@Getter
public class JwtValidationException extends RuntimeException {

    private final int code;
    private final String message;

    public JwtValidationException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
