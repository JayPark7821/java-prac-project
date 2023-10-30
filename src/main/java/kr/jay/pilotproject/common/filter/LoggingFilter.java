package kr.jay.pilotproject.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import kr.jay.pilotproject.common.utils.BufferedRequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Integer.MIN_VALUE)
@Component
public class LoggingFilter extends OncePerRequestFilter {
	private static final String TRX_ID = "REQUEST_TRACE_ID";
	private static final String TRX_TIME = "REQUEST_TIME";

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		String trxId = UUID.randomUUID().toString().substring(0, 8);
		MDC.put(TRX_ID, trxId);
		MDC.put(TRX_TIME, String.valueOf(System.currentTimeMillis()));
		request = logRequest(request);

		filterChain.doFilter(request, response);

		log.info("Ended ==>  run-time : {} ms",
			MDC.get(TRX_TIME) != null ? (System.currentTimeMillis() - Long.parseLong(MDC.get(TRX_TIME))) : 0);
		MDC.clear();
	}

	private HttpServletRequest logRequest(HttpServletRequest request) throws IOException {
		// [Method] : endpoint
		log.info("Started ==> [{}] : {}", request.getMethod(), request.getRequestURI());
		// query :
		if (StringUtils.hasText(request.getQueryString()))
			log.info("query : " + request.getQueryString());

		String contentType = request.getContentType();
		if (contentType != null && !contentType.equals(MediaType.MULTIPART_FORM_DATA_VALUE)) {
			String requestBody = readBody(request);
			if (!requestBody.isEmpty()) {
				// request body :
				log.info("request body : ");
				log.info(requestBody);
				request = new BufferedRequestWrapper(request, requestBody);
			}
		}
		return request;
	}

	private String readBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try (InputStream inputStream = request.getInputStream()) {
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return stringBuilder.toString();
	}
}
