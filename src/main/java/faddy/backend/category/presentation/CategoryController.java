package faddy.backend.category.presentation;

import faddy.backend.category.domain.dto.CategoryPairDto;
import faddy.backend.category.domain.dto.response.CategoryIdPairDto;
import faddy.backend.category.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(value = "content에서 선택한 카테고리 저장 및 부모 , 자식 카테고리 엔티티 키 매핑" ,  notes = "..")
    @PostMapping()
    public ResponseEntity<CategoryIdPairDto> saveCategories(@RequestBody @Valid CategoryPairDto categoryPairs) {

        CategoryIdPairDto response = new CategoryIdPairDto();
        response.setContentType(categoryPairs.getContentType());

        for (Map<String , String> categoryPair : categoryPairs.getCategoryPairs()) {
            Map<Long , Long> idPair = categoryService.createAndLinkCategories(categoryPair , categoryPairs.getContentType());

            response.getLinkedCategoryPairSet().add(idPair);
        }

        System.out.println("response = " + response.getLinkedCategoryPairSet().toString());

        return ResponseEntity.ok().body(
            response
        );
    }

}
