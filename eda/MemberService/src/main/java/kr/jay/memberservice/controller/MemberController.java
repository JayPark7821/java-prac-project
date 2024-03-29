package kr.jay.memberservice.controller;

import kr.jay.memberservice.dto.ModifyUserDto;
import kr.jay.memberservice.dto.RegisterUserDto;
import kr.jay.memberservice.entity.UserEntity;
import kr.jay.memberservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@RestController
public class MemberController {

    private final UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/member/users/registration")
    UserEntity registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return userService.registerUser(
            registerUserDto.getLoginId(),
            registerUserDto.getUserName()
        );
    }

    @PutMapping("/member/users/{userId}/modify")
    UserEntity modifyUser(
        @PathVariable("userId") String userId,
        @RequestBody ModifyUserDto modifyUserDto
    ) {
        return userService.modifyUser(
            userId,
            modifyUserDto.getUserName()
        );
    }

    @PostMapping("/member/users/{loginId}/login")
    UserEntity login(@PathVariable("loginId") String loginId) {
        return userService.getUser(loginId);
    }
}
