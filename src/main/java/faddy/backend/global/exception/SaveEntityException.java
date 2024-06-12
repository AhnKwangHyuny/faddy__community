package faddy.backend.global.exception;


import lombok.Getter;

@Getter
public class SaveEntityException extends RuntimeException {

    private String message;
    private int code;

    public SaveEntityException(ExceptionCode exceptionCode) {
        this.message = exceptionCode.getMessage();
        this.code = exceptionCode.getCode();
    }


    public SaveEntityException(final int code , final String message) {
        this.code = code;
        this.message = message;
    }

    public SaveEntityException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
