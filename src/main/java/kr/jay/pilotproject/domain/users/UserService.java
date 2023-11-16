package kr.jay.pilotproject.domain.users;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.jay.pilotproject.domain.users.command.UserJoinCommand;
import kr.jay.pilotproject.infrastructure.persistance.users.UserReader;
import kr.jay.pilotproject.infrastructure.persistance.users.UserStore;
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
@Transactional
public class UserService {

	private final UserStore userStore;
	private final UserReader userReader;

	public User join(final UserJoinCommand command) {
		final User user = new User(command.name());
		return userStore.save(user);
	}

	public List<User> findAllUsers() {
		return userReader.findAll();
	}
	public User getById(final Long userId) {
		return userReader.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	public void updateUserName(Long id, String name){
		userReader.findById(id)
			.ifPresent(user -> {
				user.changeName(name);
			});
	}
}
