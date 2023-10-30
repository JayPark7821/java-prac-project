package kr.jay.pilotproject.infrastructure.persistance.users;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotproject.domain.users.User;

/**
 * UserJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
