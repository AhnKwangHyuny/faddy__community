package faddy.backend.global.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ErrorApiResponse extends ApiResponse {
    private ErrorApiResponse(int status, String errorMessage) {
        super(status, errorMessage);
    }

    public static ResponseEntity<ErrorApiResponse> of(HttpStatus status, String errorMessage) {
        return ResponseEntity.status(status).body(new ErrorApiResponse(status.value(), errorMessage));
    }

    public static ResponseEntity<ErrorApiResponse> of(int status, String errorMessage) {
        return ResponseEntity.status(status).body(new ErrorApiResponse(status, errorMessage));
    }
}
