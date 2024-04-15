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

}
