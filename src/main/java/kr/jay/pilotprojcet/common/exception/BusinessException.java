package kr.jay.pilotprojcet.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * BusinessException
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCodes errorCode;
	private final String[] args;

	public BusinessException(final ErrorCodes errorCode, final String[] args) {
		super(String.format(errorCode.getMessage(), args));
		this.errorCode = errorCode;
		this.args = args;

	}

	public BusinessException(final ErrorCodes errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.args = null;
	}

}
