package kr.jay.migration.domain.recent.user;

import org.springframework.data.repository.CrudRepository;

/**
 * RecentUserRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface RecentUserRepository extends CrudRepository<RecentUser, Long> {

}
