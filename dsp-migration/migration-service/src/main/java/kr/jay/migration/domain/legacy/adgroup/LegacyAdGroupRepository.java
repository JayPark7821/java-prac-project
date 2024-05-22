package kr.jay.migration.domain.legacy.adgroup;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * LegacyAdGroupRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface LegacyAdGroupRepository extends CrudRepository<LegacyAdGroup, Long> {

    List<LegacyAdGroup> findAllByCampaignIdAndDeletedAtIsNull(Long campaignId);

}
