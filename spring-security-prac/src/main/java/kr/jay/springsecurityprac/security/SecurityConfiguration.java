package kr.jay.springsecurityprac.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.jay.springsecurityprac.security.authentication.application.UserAuthenticationFacade;
import kr.jay.springsecurityprac.security.authentication.config.UserAuthenticationConfigurer;
import kr.jay.springsecurityprac.security.authentication.filter.UserAuthenticationFilter;
import kr.jay.springsecurityprac.security.authentication.handler.UserAuthenticationFailureHandler;
import kr.jay.springsecurityprac.security.authentication.handler.UserAuthenticationSuccessHandler;
import kr.jay.springsecurityprac.security.authorization.config.JwtAuthorizationConfigurer;
import kr.jay.springsecurityprac.security.token.JwtTokenProvider;
import kr.jay.springsecurityprac.security.token.JwtTokenService;
import lombok.RequiredArgsConstructor;

/**
 * SecurityConfiguration
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 12.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final ObjectMapper objectMapper;
	private final JwtTokenService tokenService;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserAuthenticationFacade userAuthenticationFacade;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(
		HttpSecurity http,
		AuthorizationManager<RequestAuthorizationContext> authorizationManager
	) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.sessionManagement(configurer ->
				configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize ->
				authorize
					.anyRequest().access(authorizationManager)
			)
			.with(
				new UserAuthenticationConfigurer(
					new UserAuthenticationFilter(objectMapper),
					new UserAuthenticationSuccessHandler(objectMapper, tokenService),
					new UserAuthenticationFailureHandler()
				),
				Customizer.withDefaults()
			)
			.with(
				new JwtAuthorizationConfigurer(jwtTokenProvider, userAuthenticationFacade),
				Customizer.withDefaults()
			);

		return http.getOrBuild();
	}
}
