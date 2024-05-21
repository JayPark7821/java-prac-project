package kr.jay.migration.application.legacy.adgroup;

import kr.jay.migration.application.LegacyConverter;
import kr.jay.migration.application.LegacyMigrationService;
import kr.jay.migration.application.legacy.user.LegacyUserConverter;
import kr.jay.migration.domain.legacy.adgroup.LegacyAdGroup;
import kr.jay.migration.domain.legacy.adgroup.LegacyAdGroupRepository;
import kr.jay.migration.domain.legacy.user.LegacyUser;
import kr.jay.migration.domain.legacy.user.LegacyUserRepository;
import kr.jay.migration.domain.recent.campaign.RecentCampaign;
import kr.jay.migration.domain.recent.campaign.RecentCampaignRepository;
import kr.jay.migration.domain.recent.user.RecentUser;
import kr.jay.migration.domain.recent.user.RecentUserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * LegacyUserMigrationService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Service
public class LegacyAdGroupMigrationService extends LegacyMigrationService<LegacyAdGroup, RecentCampaign> {
    public LegacyAdGroupMigrationService(
        LegacyAdGroupConverter converter,
        LegacyAdGroupRepository legacyRepository,
        RecentCampaignRepository recentRepository
    ) {
        super(converter, legacyRepository, recentRepository);
    }
}
