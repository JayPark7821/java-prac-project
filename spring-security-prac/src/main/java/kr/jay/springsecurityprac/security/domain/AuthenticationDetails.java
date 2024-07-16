package kr.jay.springsecurityprac.security.domain;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

/**
 * AuthenticationDetails
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@RequiredArgsConstructor
public class AuthenticationDetails implements UserDetails {

	private final AuthenticatedUser authenticatedUser;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authenticatedUser.getAuthorityNames().stream().map(SimpleGrantedAuthority::new).toList();
	}

	@Override
	public String getPassword() {
		return authenticatedUser.getPassword();
	}

	@Override
	public String getUsername() {
		return authenticatedUser.getUserId().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AuthenticationDetails that = (AuthenticationDetails)o;
		return Objects.equals(authenticatedUser.getUserId(), that.authenticatedUser.getUserId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(authenticatedUser.getUserId());
	}
}
