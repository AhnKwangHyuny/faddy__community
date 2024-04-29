package faddy.backend.snap.domain.projection;

import faddy.backend.hashTags.domain.projection.HashTagProjection;
import faddy.backend.image.domain.projection.ImageProjection;
import faddy.backend.user.domain.projection.UserProjection;

import java.time.LocalDateTime;
import java.util.List;

public interface SnapProjection {
    String getDescription();
    LocalDateTime getCreated_at();
    UserProjection getUser();
    List<ImageProjection> getImages();
    List<HashTagProjection> getHashTags();
}
