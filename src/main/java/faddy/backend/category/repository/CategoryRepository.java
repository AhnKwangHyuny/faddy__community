package faddy.backend.category.repository;

import faddy.backend.category.domain.Category;
import faddy.backend.type.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Long , Category> {
    Optional<Category> findByName(String name);

    boolean existsByNameAndContentType(String name, ContentType contentType);

    Optional<Category> findByNameAndContentType(String name, ContentType contentType);

    Category save(Category category);
}
