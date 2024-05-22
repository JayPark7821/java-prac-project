package kr.jay.migration.application.dispatcher;

import kr.jay.migration.application.legacy.adgroup.LegacyAdGroupMigrationService;
import kr.jay.migration.application.legacy.campaign.LegacyCampaignMigrationService;
import kr.jay.migration.application.legacy.keyword.LegacyKeywordMigrationService;
import kr.jay.migration.application.legacy.user.LegacyUserMigrationService;
import kr.jay.migration.domain.AggregateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MigrationDispatcher
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationDispatcher {

    private final LegacyUserMigrationService userMigrationService;
    private final LegacyCampaignMigrationService legacyCampaignMigrationService;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;
    private final LegacyKeywordMigrationService legacyKeywordMigrationService;

    public boolean dispatch(Long aggregateId, AggregateType type) {
        boolean result = switch (type) {
            case USER -> userMigrationService.migrate(aggregateId);
            case CAMPAIGN -> legacyCampaignMigrationService.migrate(aggregateId);
            case ADGROUP -> legacyAdGroupMigrationService.migrate(aggregateId);
            case KEYWORD -> legacyKeywordMigrationService.migrate(aggregateId);
        };

        if(result) {
            log.info("{}", LegacyMigrationLog.success(type, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(type, aggregateId));
        }
        return result;
    }
}
