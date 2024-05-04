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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        String token = request.getHeader("Authorization");

        // Authorization 헤더가 없는 경우
        if (token == null || token.isEmpty()) {
            // 401 Unauthorized 에러 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied: No Authorization token provided.");
        }

        try {
            // 토큰으로 부터 username 얻기 (follower)
            String username = jwtUtil.getUsername(token);

            //username을 통해 user 조회
            User follower = userService.findByUsername(username);
            User followee = userService.findByUsername(requestDto.getUsername());

            String message = followService.follow(follower, followee);

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
     * 팔로잉 조회
     */
    @GetMapping("/users/{userName}/following")
    public ResponseEntity<List<FollowDTO>> getFollowingList(@PathVariable("userName") String userName, Authentication auth) {
        User from_user = userService.findUser(userName);
        User requestUser=userService.findUser(auth.getName());
        return ResponseEntity.ok().body(followService.followingList(from_user, requestUser));
    }

    /**
     * 팔로워 조회
     */
    @GetMapping("/users/{userName}/follower")
    public ResponseEntity<List<FollowDTO>> getFollowerList(@PathVariable("userName") String userName, Authentication auth) {
        User to_user = userService.findUser(userName);
        User requestUser=userService.findUser(auth.getName());
        return ResponseEntity.ok().body(followService.followerList(to_user, requestUser));
    }

    /**
     * 친구 끊기
     */
    @DeleteMapping("/users/follow/{friendName}")
    public ResponseEntity<String> deleteFollow(Authentication authentication){
        return ResponseEntity.ok().body(followService.cancelFollow(userService.findUser(authentication.getName())));
    }
}