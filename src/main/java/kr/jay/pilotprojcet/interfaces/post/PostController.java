package kr.jay.pilotprojcet.interfaces.post;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.jay.pilotprojcet.domain.post.PostService;
import kr.jay.pilotprojcet.interfaces.dto.PostCreateRequest;
import kr.jay.pilotprojcet.interfaces.dto.PostResponse;
import lombok.RequiredArgsConstructor;

/**
 * PostController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

	private final PostService postService;

	@PostMapping
	public void createPost(final PostCreateRequest request) {
		postService.save(request.toCommand());
	}

	@GetMapping
	public List<PostResponse> findAllPosts() {
		return postService.findAllPosts().stream()
			.map(PostResponse::new)
			.toList();
	}
}
