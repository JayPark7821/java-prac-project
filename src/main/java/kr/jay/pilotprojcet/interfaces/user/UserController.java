package kr.jay.pilotprojcet.interfaces.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.pilotprojcet.domain.users.UserService;
import kr.jay.pilotprojcet.interfaces.dto.UserJoinRequest;
import kr.jay.pilotprojcet.interfaces.dto.UserResponse;
import lombok.RequiredArgsConstructor;

/**
 * UserController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public UserResponse join(@RequestBody final UserJoinRequest userJoinRequest) {
		return new UserResponse(userService.join(userJoinRequest.toCommand()));
	}
}
