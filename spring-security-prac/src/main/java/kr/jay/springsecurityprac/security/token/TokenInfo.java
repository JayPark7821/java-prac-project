package kr.jay.springsecurityprac.security.token;

/**
 * TokenInfo
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
public record TokenInfo(
	String accessToken,
	String refreshToken
) {
}
