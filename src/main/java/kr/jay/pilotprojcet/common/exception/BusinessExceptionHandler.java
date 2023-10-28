package kr.jay.pilotprojcet.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * BusinessExceptionHandler
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RestControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<String> handle(final BusinessException e) {
		return ResponseEntity
			.status(e.getErrorCode().getStatus())
			.body(e.getArgs() != null
				? e.getErrorCode().getMessage()
				: String.format(e.getErrorCode().getMessage(), e.getArgs())
			);
	}
}
