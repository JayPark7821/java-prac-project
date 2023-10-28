package kr.jay.pilotprojcet.domain.post.command;

import kr.jay.pilotprojcet.domain.post.Post;

/**
 * PostCreateCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public record PostCreateCommand(
	Long userId,
	String title,
	String content
) {
}
