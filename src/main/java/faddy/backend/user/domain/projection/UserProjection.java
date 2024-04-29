package faddy.backend.user.domain.projection;

import faddy.backend.profile.domain.projection.ProfileProjection;

public interface UserProjection {
    Long getId();
    ProfileProjection getProfile();
    String getUsername();
    String getNickname();
    String getEmail();
}
