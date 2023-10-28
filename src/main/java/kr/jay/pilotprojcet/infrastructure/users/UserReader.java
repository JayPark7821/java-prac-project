package kr.jay.pilotprojcet.infrastructure.users;

import java.util.Optional;

import org.springframework.stereotype.Component;

import kr.jay.pilotprojcet.domain.users.User;
import lombok.RequiredArgsConstructor;

/**
 * UserReader
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class UserReader {

	private final UserJpaRepository userJpaRepository;

	public Optional<User> findById(final Long userId) {
		return userJpaRepository.findById(userId);
	}
}
