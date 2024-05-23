package faddy.backend.snap.infrastructure.mapper;

import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.image.domain.Image;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.response.SnapResponseDto;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T21:50:41+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class SnapMapperImpl implements SnapMapper {

    @Override
    public SnapResponseDto toDto(Snap snap) {
        if ( snap == null ) {
            return null;
        }

        SnapResponseDto snapResponseDto = new SnapResponseDto();

        snapResponseDto.setUser( toUserDto( snap.getUser() ) );
        snapResponseDto.setHashTags( toHashTagDtos( snap.getHashTags() ) );
        snapResponseDto.setDescription( snap.getDescription() );
        snapResponseDto.setCreated_at( snap.getCreated_at() );

        return snapResponseDto;
    }

    @Override
    public SnapResponseDto.UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        SnapResponseDto.UserDto.UserDtoBuilder userDto = SnapResponseDto.UserDto.builder();

        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.nickname( user.getNickname() );
        userDto.email( user.getEmail() );
        userDto.profile( toProfileDto( user.getProfile() ) );

        return userDto.build();
    }

    @Override
    public SnapResponseDto.ProfileDto toProfileDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        SnapResponseDto.ProfileDto.ProfileDtoBuilder profileDto = SnapResponseDto.ProfileDto.builder();

        profileDto.id( profile.getId() );
        profileDto.motto( profile.getMotto() );
        if ( profile.getUserLevel() != null ) {
            profileDto.userLevel( profile.getUserLevel().name() );
        }

        return profileDto.build();
    }

    @Override
    public List<SnapResponseDto.ImageDto> toImageDtos(List<Image> images) {
        if ( images == null ) {
            return null;
        }

        List<SnapResponseDto.ImageDto> list = new ArrayList<SnapResponseDto.ImageDto>( images.size() );
        for ( Image image : images ) {
            list.add( toImageDto( image ) );
        }

        return list;
    }

    @Override
    public SnapResponseDto.ImageDto toImageDto(Image image) {
        if ( image == null ) {
            return null;
        }

        SnapResponseDto.ImageDto.ImageDtoBuilder imageDto = SnapResponseDto.ImageDto.builder();

        imageDto.imageUrl( image.getImageUrl() );
        imageDto.hashName( image.getHashName() );

        return imageDto.build();
    }

    @Override
    public List<SnapResponseDto.HashTagDto> toHashTagDtos(List<HashTag> hashTags) {
        if ( hashTags == null ) {
            return null;
        }

        List<SnapResponseDto.HashTagDto> list = new ArrayList<SnapResponseDto.HashTagDto>( hashTags.size() );
        for ( HashTag hashTag : hashTags ) {
            list.add( toHashTagDto( hashTag ) );
        }

        return list;
    }

    @Override
    public SnapResponseDto.HashTagDto toHashTagDto(HashTag hashTag) {
        if ( hashTag == null ) {
            return null;
        }

        SnapResponseDto.HashTagDto.HashTagDtoBuilder hashTagDto = SnapResponseDto.HashTagDto.builder();

        hashTagDto.name( hashTag.getName() );

        return hashTagDto.build();
    }
}
