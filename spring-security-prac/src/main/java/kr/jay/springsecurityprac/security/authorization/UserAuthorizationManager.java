package kr.jay.springsecurityprac.security.authorization;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import kr.jay.springsecurityprac.security.domain.AuthenticationDetails;
import kr.jay.springsecurityprac.user.authority.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserAuthorizationManager
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
		HttpServletRequest request = context.getRequest();
		String requestURI = request.getRequestURI();

		AuthenticationDetails principal = (AuthenticationDetails)authentication.get().getPrincipal();

		boolean accessible = principal.getAuthorities()
			.stream()
			.flatMap(authority -> UserRole.getPermittedUrlsBy(authority.getAuthority()).stream())
			.anyMatch(url -> url.equals(requestURI));
			// .flatMap(authority -> UserRole.getPermittedUrlsBy(authority.getAuthority()))

		return new AuthorizationDecision(accessible);
	}
}
