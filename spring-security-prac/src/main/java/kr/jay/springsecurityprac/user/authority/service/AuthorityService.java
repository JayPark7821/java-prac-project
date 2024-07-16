package kr.jay.springsecurityprac.user.authority.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.jay.springsecurityprac.user.authority.Authority;
import kr.jay.springsecurityprac.user.authority.UserRole;
import lombok.RequiredArgsConstructor;

/**
 * AuthorityService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2024. 7. 13.
 */
@RequiredArgsConstructor
@Service
public class AuthorityService {

	public List<Authority> getAuthorityByUserId(Long userId) {
		return List.of(new Authority(UserRole.USER));
	}
}
