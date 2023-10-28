package kr.jay.pilotprojcet.infrastructure.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import kr.jay.pilotprojcet.domain.post.Post;
import lombok.RequiredArgsConstructor;

/**
 * PostStore
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Component
@RequiredArgsConstructor
public class PostStore {

	private final PostJpaRepository postJpaRepository;

	public Post save(final Post post) {
		return postJpaRepository.save(post);
	}
}
