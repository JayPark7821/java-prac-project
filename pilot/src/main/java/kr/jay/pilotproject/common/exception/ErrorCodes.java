package kr.jay.pilotproject.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ErrorCodes
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
	BUSINESS_EXCEPTION(HttpStatus.BAD_REQUEST, "Business Exception %s.....%s...."),
	;

	private final HttpStatus status;
	private final String message;
}
