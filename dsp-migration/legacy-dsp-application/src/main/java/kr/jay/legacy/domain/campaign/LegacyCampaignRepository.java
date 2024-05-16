package kr.jay.legacy.domain.campaign;

import kr.jay.legacy.domain.user.LegacyUser;
import org.springframework.data.repository.CrudRepository;

/**
 * LegacyCampaignRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface LegacyCampaignRepository extends CrudRepository<LegacyCampaign, Long> {

}
