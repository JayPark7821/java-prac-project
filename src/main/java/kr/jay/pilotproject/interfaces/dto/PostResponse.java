package kr.jay.pilotproject.interfaces.dto;

import kr.jay.pilotproject.domain.post.Post;

/**
 * PostResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public record PostResponse(
	Long id,
	String title,
	String content,
	String authorName
) {

	public PostResponse(final Post post) {
		this(post.getId(), post.getTitle(), post.getContent(), post.getUser().getName());
	}
}
