//package faddy.backend.snap.infrastructure;
//
//import faddy.backend.hashTags.domain.projection.HashTagProjection;
//import faddy.backend.image.domain.projection.ImageProjection;
//import faddy.backend.profile.domain.projection.ProfileProjection;
//import faddy.backend.snap.domain.projection.SnapProjection;
//import faddy.backend.snap.domain.dto.response.SnapResponseDto;
//import faddy.backend.user.domain.projection.UserProjection;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//public class SnapMapper {
//    public static SnapResponseDto mapToDto(SnapProjection snapProjection) {
//        return SnapResponseDto(
//                .user(mapToUserDto(snapProjection.getUser()))
//                .images(snapProjection.getImages().stream()
//                        .map(SnapMapper::mapToImageDto)
//                        .collect(Collectors.toList()))
//                .hashTags(snapProjection.getHashTags().stream()
//                        .map(SnapMapper::mapToHashTagDto)
//                        .collect(Collectors.toList()))
//                .description(snapProjection.getDescription())
//                .createdAt(snapProjection.getCreated_at())
//                .build();
//            )
//    }
//
//    private static SnapResponseDto.UserDto mapToUserDto(UserProjection userProjection) {
//        return SnapResponseDto.UserDto.builder()
//                .id(userProjection.getId())
//                .usernameField(userProjection.getUsername())
//                .nickname(userProjection.getNickname())
//                .email(userProjection.getEmail())
//                .build();
//    }
//
//    private static SnapResponseDto.ProfileDto mapToProfileDto(ProfileProjection profileProjection) {
//        return SnapResponseDto.ProfileDto.builder()
//                .id(profileProjection.getId())
//                .motto(profileProjection.getMotto())
//                .userLevel(profileProjection.getUserLevel())
//                .build();
//    }
//
//    private static SnapResponseDto.ImageDto mapToImageDto(ImageProjection imageProjection) {
//        return SnapResponseDto.ImageDto.builder()
//                .imageUrl(imageProjection.getImageUrl())
//                .hashedName(imageProjection.getHashedName())
//                .build();
//    }
//
//    private static SnapResponseDto.HashTagDto mapToHashTagDto(HashTagProjection hashTagProjection) {
//        return SnapResponseDto.HashTagDto.builder()
//                .name(hashTagProjection.getName())
//                .build();
//    }
//}