package faddy.backend.category.presentation;

import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.exception.BadRequestException;
import faddy.backend.global.exception.ExceptionCode;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @ApiOperation(value = "content에서 선택한 카테고리 저장 및 부모 , 자식 카테고리 키 매핑"  notes = )
    @PostMapping()
    public ResponseEntity<ResponseDto> saveCategories(@RequestBody) {


    }

}
