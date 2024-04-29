package faddy.backend.snap.repository;

import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.response.SnapResponseDto;

import java.util.Optional;

public interface CustomSnapRepository {
    Optional<SnapResponseDto> findSnapWithSnapDto(Long snapId);

    Optional<Snap> findSnapById(Long snapId);
}
