package kr.jay.springsecurityprac.security.authorization.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.jay.springsecurityprac.security.authentication.application.UserAuthenticationFacade;
import kr.jay.springsecurityprac.security.token.JwtTokenProvider;
import kr.jay.springsecurityprac.user.authority.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtAuthorizationFilter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private static final String GRANT_TYPE = "Bearer ";

	private final JwtTokenProvider tokenProvider;
	private final UserAuthenticationFacade userAuthenticationFacade;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		String authorization = getAuthorizationHeaderFrom(request);

		if (validToken(authorization)) {
			String token = authorization.substring(GRANT_TYPE.length());
			log.info("accessToken = {}", token);

			String username = tokenProvider.getUsername(token);
			log.info("Authority username = {}", username);

			UserDetails userDetails = userAuthenticationFacade.loadUserByUsername(username);
			Authentication authenticated = createAuthentication(userDetails);
			setSecurityContext(authenticated);
		}

		filterChain.doFilter(request, response);
	}

	private String getAuthorizationHeaderFrom(HttpServletRequest request) {
		return request.getHeader(HttpHeaders.AUTHORIZATION);
	}

	private boolean validToken(String token) {
		return StringUtils.hasText(token) && token.startsWith(GRANT_TYPE);
	}

	private UsernamePasswordAuthenticationToken createAuthentication(UserDetails userDetails) {
		return UsernamePasswordAuthenticationToken.authenticated(
			userDetails,
			userDetails.getPassword(),
			userDetails.getAuthorities()
		);
	}

	private void setSecurityContext(Authentication authenticated) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authenticated);
		SecurityContextHolder.setContext(context);
	}
}
