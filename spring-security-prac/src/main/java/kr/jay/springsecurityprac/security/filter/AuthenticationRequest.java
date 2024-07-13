package kr.jay.springsecurityprac.security.filter;

import jakarta.validation.constraints.NotNull;

/**
 * AuthenticationRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
public record AuthenticationRequest(
	@NotNull
	String userId,
	@NotNull
	String password
) {
}
