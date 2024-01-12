package kr.jay.pilotproject.infrastructure.persistance.prod.users;

import kr.jay.pilotproject.domain.prod.ProdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public interface ProdUserJpaRepository extends JpaRepository<ProdUser, Long> {

}
