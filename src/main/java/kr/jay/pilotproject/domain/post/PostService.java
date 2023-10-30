package kr.jay.pilotproject.domain.post;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import kr.jay.pilotproject.domain.users.User;
import kr.jay.pilotproject.domain.users.UserService;
import kr.jay.pilotproject.infrastructure.persistance.post.PostReader;
import kr.jay.pilotproject.infrastructure.persistance.post.PostStore;
import lombok.RequiredArgsConstructor;

/**
 * PostService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostStore postStore;
	private final PostReader postReader;
	private final UserService userService;

	public List<Post> findAllPosts() {
		return postReader.findAll();
	}

	public Post save(final PostCreateCommand command) {
		final User user = userService.getById(command.userId());
		return postStore.save(new Post(command.title(), command.content(), user));
	}

}
