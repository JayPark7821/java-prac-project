package kr.jay.springsecurityprac.security.domain;

import java.util.List;

import kr.jay.springsecurityprac.user.User;
import kr.jay.springsecurityprac.user.authority.Authority;
import lombok.Getter;

/**
 * AuthenticatedUser
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Getter
public class AuthenticatedUser {

	private Long userId;
	private String userName;
	private String password;
	private List<String> authorityNames;

	public AuthenticatedUser(User user, List<Authority> authorities) {
		this.userId = user.getUserId();
		this.userName = user.getUsername();
		this.password = user.getPassword();
		this.authorityNames = authorities.stream().map(authority -> authority.getUserRole().getAuthority()).toList();
	}
}
