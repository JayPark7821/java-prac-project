package kr.jay.springsecurityprac.security.authorization.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import kr.jay.springsecurityprac.security.authentication.application.UserAuthenticationFacade;
import kr.jay.springsecurityprac.security.authorization.filter.JwtAuthorizationFilter;
import kr.jay.springsecurityprac.security.token.JwtTokenProvider;
import kr.jay.springsecurityprac.user.authority.service.AuthorityService;
import lombok.RequiredArgsConstructor;

/**
 * JwtAuthorizationConfigurer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@RequiredArgsConstructor
public class JwtAuthorizationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final JwtTokenProvider tokenProvider;
	private final UserAuthenticationFacade userAuthenticationFacade;

	/**
	 * 구성자 설정
	 */
	@Override
	public void configure(HttpSecurity builder) {
		builder.addFilterBefore(
			new JwtAuthorizationFilter(tokenProvider, userAuthenticationFacade),
			AuthorizationFilter.class
		);
	}
}
