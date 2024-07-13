package kr.jay.springsecurityprac.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.jay.springsecurityprac.user.User;
import kr.jay.springsecurityprac.user.authority.Authority;
import kr.jay.springsecurityprac.user.authority.service.AuthorityService;
import kr.jay.springsecurityprac.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserAuthenticationFacade
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthenticationFacade implements UserDetailsService {

	private final UserService userService;
	private final AuthorityService authorityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		List<Authority> authorities = authorityService.getAuthorityByUserId(user.getUserId());

		return new AuthenticationDetails(new AuthenticatedUser(user, authorities));
	}
}
