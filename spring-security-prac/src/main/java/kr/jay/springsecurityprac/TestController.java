package kr.jay.springsecurityprac;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 14.
 */

@RestController
public class TestController {

	@GetMapping("/auth-test")
	String authTest() {
		return "requested";
	}
}
