package kr.jay.springsecurityprac.user.authority;

import lombok.Getter;

/**
 * Authority
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Getter
public class Authority {
	private UserRole userRole;

	public Authority(UserRole userRole) {
		this.userRole = userRole;
	}
}
