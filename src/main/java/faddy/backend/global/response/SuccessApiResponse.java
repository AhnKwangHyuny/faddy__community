package faddy.backend.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class SuccessApiResponse<T> extends ApiResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private SuccessApiResponse(T data) {
        super(HttpStatus.OK.value(), "성공");
        this.data = data;
    }

    private SuccessApiResponse() {
        super(HttpStatus.OK.value(), "성공");
    }

    public static <T> ResponseEntity<SuccessApiResponse<T>> of(T data) {
        return ResponseEntity.ok(new SuccessApiResponse<>(data));
    }

    public static <T> ResponseEntity<SuccessApiResponse<T>> of() {
        return ResponseEntity.ok(new SuccessApiResponse<>());
    }
}
