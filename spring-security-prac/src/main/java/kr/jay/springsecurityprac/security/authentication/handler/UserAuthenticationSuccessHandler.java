package kr.jay.springsecurityprac.security.authentication.handler;

import static org.springframework.http.HttpStatus.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.jay.springsecurityprac.security.token.JwtTokenService;
import lombok.RequiredArgsConstructor;

/**
 * UserAuthenticationSuccessHandler
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final ObjectMapper objectMapper;
	private final JwtTokenService tokenService;

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException, ServletException {
		sendResponse(response, tokenService.generateToken(authentication.getName()));
	}

	private void sendResponse(HttpServletResponse response,Object value) throws IOException {
		response.setStatus(OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		objectMapper.writeValue(response.getWriter(), value);
	}
}
