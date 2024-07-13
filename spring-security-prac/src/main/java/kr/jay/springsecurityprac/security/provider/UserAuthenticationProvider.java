package kr.jay.springsecurityprac.security.provider;

import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserAuthenticationProvider
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Override
	protected void additionalAuthenticationChecks(
		UserDetails userDetails,
		UsernamePasswordAuthenticationToken authentication
	) throws AuthenticationException {
		if (Objects.isNull(authentication.getCredentials())) {
			throw new BadCredentialsException(
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials")
			);
		}

		// 비밀번호 검증
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			throw new BadCredentialsException(
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials")
			);
		}

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws
		AuthenticationException {
		return userDetailsService.loadUserByUsername(username);
	}
}
