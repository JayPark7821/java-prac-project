package kr.jay.pilotprojcet.infrastructure.users;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * UserReader
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class UserReader {

	private final UserJpaRepository userJpaRepository;


}
