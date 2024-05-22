package kr.jay.migration.application.legacy.campaign;

import kr.jay.migration.application.LegacyMigrationService;
import kr.jay.migration.application.legacy.adgroup.LegacyAdGroupMigrationService;
import kr.jay.migration.domain.legacy.adgroup.LegacyAdGroupRepository;
import kr.jay.migration.domain.legacy.campaign.LegacyCampaign;
import kr.jay.migration.domain.recent.campaign.RecentCampaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * LegacyCampaignMigrationService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Service
public class LegacyCampaignMigrationService extends LegacyMigrationService<LegacyCampaign, RecentCampaign> {

    private final LegacyAdGroupRepository legacyAdGroupRepository;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;

    public LegacyCampaignMigrationService(
        CrudRepository<LegacyCampaign, Long> legacyRepository,
        CrudRepository<RecentCampaign, Long> recentRepository,
        LegacyAdGroupRepository legacyAdGroupRepository,
        LegacyAdGroupMigrationService legacyAdGroupMigrationService
    ) {
        super(null, legacyRepository, recentRepository);
        this.legacyAdGroupRepository = legacyAdGroupRepository;
        this.legacyAdGroupMigrationService = legacyAdGroupMigrationService;
    }

    @Override
    protected void migrate(LegacyCampaign legacyCampaign) {
        legacyAdGroupRepository.findAllByCampaignIdAndDeletedAtIsNull(legacyCampaign.getId())
            .forEach(campaign -> legacyAdGroupMigrationService.migrate(campaign.getId()));
    }
}
