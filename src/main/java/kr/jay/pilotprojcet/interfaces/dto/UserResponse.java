package kr.jay.pilotprojcet.interfaces.dto;

import kr.jay.pilotprojcet.domain.users.User;

/**
 * UserResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public record UserResponse(
	Long id,
	String name
) {
	public UserResponse(final User user) {
		this( user.getId(), user.getName());
	}
}
