package faddy.backend.profile.repository;

import faddy.backend.user.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {


}
