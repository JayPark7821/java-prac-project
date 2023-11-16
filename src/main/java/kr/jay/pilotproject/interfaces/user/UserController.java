package kr.jay.pilotproject.interfaces.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.pilotproject.domain.users.UserService;
import kr.jay.pilotproject.interfaces.dto.UserJoinRequest;
import kr.jay.pilotproject.interfaces.dto.UserResponse;
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

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.findAllUsers().stream().map(UserResponse::new).toList();
	}
	@PatchMapping
	public void updateUserName(@RequestParam("id") Long id, @RequestParam("name") String name) {
		userService.updateUserName(id, name);
	}
}
