package kr.jay.migration.domain.migration.user;

import org.springframework.data.repository.CrudRepository;

/**
 * MigrationUserRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public interface MigrationUserRepository extends CrudRepository<MigrationUser, Long> {

}
