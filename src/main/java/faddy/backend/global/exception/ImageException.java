package faddy.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ImageException extends RuntimeException {

    private final int code;
    private final String message;

    public ImageException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public ImageException(HttpStatus httpStatus , String message) {
        this.code = httpStatus.value();
        this.message = message;
    }
}