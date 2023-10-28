package kr.jay.pilotprojcet.domain.users;

import org.springframework.stereotype.Service;

import kr.jay.pilotprojcet.domain.users.command.UserJoinCommand;
import kr.jay.pilotprojcet.infrastructure.users.UserReader;
import kr.jay.pilotprojcet.infrastructure.users.UserStore;
import lombok.RequiredArgsConstructor;

/**
 * UserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserStore userStore;
	private final UserReader userReader;

	public User join(final UserJoinCommand command) {
		final User user = new User(command.name());
		return userStore.save(user);
	}
}
