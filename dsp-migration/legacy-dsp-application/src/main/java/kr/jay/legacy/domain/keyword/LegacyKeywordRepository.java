package kr.jay.legacy.domain.keyword;

import kr.jay.legacy.domain.campaign.LegacyCampaign;
import org.springframework.data.repository.CrudRepository;

/**
 * LegacyKeywordRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface LegacyKeywordRepository extends CrudRepository<LegacyKeyword, Long> {

}
