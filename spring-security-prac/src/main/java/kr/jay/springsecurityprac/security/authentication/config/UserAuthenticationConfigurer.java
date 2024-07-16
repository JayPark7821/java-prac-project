package kr.jay.springsecurityprac.security.authentication.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/**
 * UserAuthenticationConfigurer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 12.
 */

@RequiredArgsConstructor
public class UserAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final AbstractAuthenticationProcessingFilter authenticationFilter;
	private final AuthenticationSuccessHandler successHandler;
	private final AuthenticationFailureHandler failureHandler;

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		authenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
		authenticationFilter.setAuthenticationSuccessHandler(successHandler);
		authenticationFilter.setAuthenticationFailureHandler(failureHandler);
		builder.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
