package kr.jay.springsecurityprac.user.service;

import org.springframework.stereotype.Service;

import kr.jay.springsecurityprac.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	public User getUserByUsername(String username) {
		return new User();
	}
}
