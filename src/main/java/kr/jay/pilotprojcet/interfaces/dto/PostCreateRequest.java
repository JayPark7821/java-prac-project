package kr.jay.pilotprojcet.interfaces.dto;

import kr.jay.pilotprojcet.domain.post.command.PostCreateCommand;

/**
 * PostCreateRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public record PostCreateRequest(
	Long userId,
	String title,
	String content
) {
	public PostCreateCommand toCommand() {
		return new PostCreateCommand(userId(), title(), content());
	}
}
