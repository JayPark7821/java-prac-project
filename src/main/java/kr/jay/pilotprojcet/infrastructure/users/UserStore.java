package kr.jay.pilotprojcet.infrastructure.users;

import org.springframework.stereotype.Component;

import kr.jay.pilotprojcet.domain.users.User;
import lombok.RequiredArgsConstructor;

/**
 * UserStore
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class UserStore {

	private final UserJpaRepository userJpaRepository;
	public User save(final User user) {
		return userJpaRepository.save(user);
	}
}
