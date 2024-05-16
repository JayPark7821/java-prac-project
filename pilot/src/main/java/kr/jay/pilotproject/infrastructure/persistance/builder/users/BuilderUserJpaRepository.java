package kr.jay.pilotproject.infrastructure.persistance.builder.users;

import kr.jay.pilotproject.domain.builder.BuilderUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public interface BuilderUserJpaRepository extends JpaRepository<BuilderUser, Long> {

}
