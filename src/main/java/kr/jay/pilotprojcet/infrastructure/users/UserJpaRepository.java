package kr.jay.pilotprojcet.infrastructure.users;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotprojcet.domain.users.User;

/**
 * UserJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
