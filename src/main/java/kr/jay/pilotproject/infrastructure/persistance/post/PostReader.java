package kr.jay.pilotproject.infrastructure.persistance.post;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.jay.pilotproject.domain.post.Post;
import lombok.RequiredArgsConstructor;

/**
 * PostReader
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class PostReader {

	private final PostJpaRepository postJpaRepository;

	public List<Post> findAll() {
		return postJpaRepository.findAll();
	}
}
