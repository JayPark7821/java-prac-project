package kr.jay.springsecurityprac.user;

import lombok.Getter;

/**
 * User
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Getter
public class User {
	private Long userId;
	private String username;
	private String password;

	public String getPassword() {
		return "{bcrypt}$2a$10$QRYzOS7mWcq9nnu3yuOyHu2Lvf7hQ3EWgR0cPPjpvBXpJG6U6UWpO";// "test123";
	}
}
