package faddy.backend.global.exception;

public class ChatRoomException extends RuntimeException {
    private final int code;
    private final String message;

    public ChatRoomException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public ChatRoomException(final int code , final String message) {
        this.code = code;
        this.message = message;
    }

    public ChatRoomException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public int getErrorCode() {
        return this.code;
    }

    public String getErrorMessage() {
        return this.message;
    }
}
