package kr.jay.springsecurityprac.security.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * JwtTokenProviderProperties
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@Getter
@Setter
@ConfigurationProperties("security.jwt")
public class JwtTokenProviderProperties {

	/**
	 * Secret Key
	 */
	private String secret;

	/**
	 * 액세스 토큰 만료시간(초)
	 */
	private long accessTokenValidityInSeconds;

	/**
	 * 리프레시 토큰 만료시간(초)
	 */
	private long refreshTokenValidityInSeconds;
}
