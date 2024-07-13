package kr.jay.springsecurityprac.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * UserAuthenticationFilter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@Slf4j
public class UserAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final AntPathRequestMatcher DEFAULT_ANT_LOGIN_PATH_REQUEST_MATCHER
		= new AntPathRequestMatcher("/login", "POST");

	private final ObjectMapper objectMapper;

	public UserAuthenticationFilter(ObjectMapper objectMapper) {
		super(DEFAULT_ANT_LOGIN_PATH_REQUEST_MATCHER);
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException, IOException, ServletException {
		AuthenticationRequest authenticationRequest = getAuthenticationRequestFrom(request);
		String email = authenticationRequest.userId();
		String password = authenticationRequest.password();
		log.info("Login email = {} / password = {}", email, password);

		UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(email, password);
		return getAuthenticationManager().authenticate(authRequest);
	}

	private AuthenticationRequest getAuthenticationRequestFrom(HttpServletRequest request) {
		try {
			return objectMapper.readValue(request.getReader(), AuthenticationRequest.class);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());

		}
	}
}
