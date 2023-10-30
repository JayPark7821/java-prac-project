package kr.jay.pilotproject.domain.post.command;

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
