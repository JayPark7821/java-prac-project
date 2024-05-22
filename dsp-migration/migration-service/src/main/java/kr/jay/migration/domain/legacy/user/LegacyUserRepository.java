package kr.jay.migration.domain.legacy.user;

import org.springframework.data.repository.CrudRepository;

/**
 * LegacyUserRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface LegacyUserRepository extends CrudRepository<LegacyUser, Long> {

}
