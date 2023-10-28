package kr.jay.pilotprojcet.infrastructure.post;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.jay.pilotprojcet.domain.post.Post;
import kr.jay.pilotprojcet.infrastructure.users.UserJpaRepository;
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
