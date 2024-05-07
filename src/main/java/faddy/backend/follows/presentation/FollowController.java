package faddy.backend.follows.presentation;

import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.auth.jwt.Service.JwtUtil;
import faddy.backend.follows.domain.dto.request.FollowRequestDto;
import faddy.backend.follows.service.FollowService;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class FollowController {
    private final UserService userService;
    private final FollowService followService;
    private final JwtUtil jwtUtil;

    /**
     * 친구 맺기(팔로잉)
     */
    @PostMapping("/follow")
    public ResponseEntity follow(HttpServletRequest request, @RequestBody FollowRequestDto requestDto) {

        String token = jwtUtil.getTokenFromRequest(request);
        try {

            //username을 통해 user 조회
            User following = userService.findUserByToken(token);
            User follower = userService.findByUsername(requestDto.getToUsername());

            String message = followService.follow(following, follower);

            return ResponseEntity.ok().body(
                    new ResponseDto(
                        String.valueOf(HttpStatus.OK.value()),
                            message
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseDto (
                            String.valueOf(HttpStatus.BAD_REQUEST.value()),
                            e.getMessage()
                    )
            );
        }
    }

    /**
     * 언팔하기
     */
    @DeleteMapping("/follow/{toUsername}")
    public ResponseEntity<ResponseDto<String>> deleteFollow(HttpServletRequest request , @PathVariable("toUsername") String toUsername){

        // authorization token 가져오기
        String token = jwtUtil.getTokenFromRequest(request);

        // 본인과 언팔할 유저 아이디 찾기
        try {
            User following = userService.findUserByToken(token);
            User follower = userService.findByUsername(toUsername);

            // 언팔로우
            followService.cancelFollow(following , follower);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseDto (
                            String.valueOf(HttpStatus.BAD_REQUEST.value()),
                            e.getMessage()
                    )
            );
        }

        return ResponseEntity.ok().body(
                new ResponseDto<String>(
                        String.valueOf(HttpStatus.OK.value()),
                        toUsername + "과의 언팔에 성공했습니다."
                )
        );
    }

    /**
     *  특정 팔로워 조회
     * */
    @GetMapping("/follow/{toUsername}")
    public ResponseEntity<ResponseDto> CheckFollowStatus(HttpServletRequest request ,@PathVariable("toUsername") String toUsername ) {
        // authorization token 가져오기
        String token = jwtUtil.getTokenFromRequest(request);

        // 본인과 언팔할 유저 아이디 찾기
        try {
            User following = userService.findUserByToken(token);
            User follower = userService.findByUsername(toUsername);

            // follow status 확인
            return ResponseEntity.ok().body(
                    new ResponseDto<Map<String, Boolean>>(
                            String.valueOf(HttpStatus.OK.value()),
                            "success",
                            Map.of("isFollowed", followService.isFollowed(following, follower))
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseDto (
                            String.valueOf(HttpStatus.BAD_REQUEST.value()),
                            e.getMessage()
                    )
            );
        }
    }

    /**
     * 팔로잉 조회
     */
//    @GetMapping("/users/{userName}/following")
//    public ResponseEntity<List<FollowDTO>> getFollowingList(@PathVariable("userName") String userName, Authentication auth) {
//        User from_user = userService.findUser(userName);
//        User requestUser=userService.findUser(auth.getName());
//        return ResponseEntity.ok().body(followService.followingList(from_user, requestUser));
//    }
//
//    /**
//     * 팔로워 조회
//     */
//    @GetMapping("/users/{userName}/follower")
//    public ResponseEntity<List<FollowDTO>> getFollowerList(@PathVariable("userName") String userName, Authentication auth) {
//        User to_user = userService.findUser(userName);
//        User requestUser=userService.findUser(auth.getName());
//        return ResponseEntity.ok().body(followService.followerList(to_user, requestUser));
//    }
//

}