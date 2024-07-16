package kr.jay.springsecurityprac.user.authority;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * UserRole
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@Getter
@RequiredArgsConstructor
public enum UserRole {

	ADMIN("ROLE_ADMIN", List.of("")),
	USER("ROLE_USER", List.of("/auth-test"));

	private final String authority;
	private final List<String> permittedUrls;

	public static List<String> getPermittedUrlsBy(String authority) {
		return Arrays.stream(UserRole.values())
			.filter(userRole -> userRole.getAuthority().equals(authority))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid authority"))
			.getPermittedUrls();
	}
}
