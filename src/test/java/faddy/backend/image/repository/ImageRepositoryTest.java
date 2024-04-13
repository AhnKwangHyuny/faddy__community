package faddy.backend.image.repository;


import faddy.backend.image.domain.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void testDeleteAllByHashNameIn() {
        // 테스트를 위한 Image 엔티티 생성 및 저장
        Image image1 = new Image("url1", "hash1", "name1", 1000L, "jpeg");
        Image image2 = new Image("url2", "hash2", "name2", 2000L, "png");

        imageRepository.save(image1);
        imageRepository.save(image2);

        // 삭제 전 데이터베이스에 엔티티가 존재하는지 확인
        List<Image> imagesBefore = imageRepository.findAll();
        assertThat(imagesBefore).hasSize(2);

        // 삭제 로직 실행
        imageRepository.deleteAllByHashNameIn(Arrays.asList("hash1", "hash2"));

        // 삭제 후 데이터베이스 상태 확인
        List<Image> imagesAfter = imageRepository.findAll();
        assertThat(imagesAfter).isEmpty(); // 모든 Image 엔티티가 삭제되었는지 확인
    }
}
