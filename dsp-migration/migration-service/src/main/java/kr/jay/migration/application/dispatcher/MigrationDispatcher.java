package kr.jay.migration.application.dispatcher;

import kr.jay.migration.application.legacy.adgroup.LegacyAdGroupMigrationService;
import kr.jay.migration.application.legacy.campaign.LegacyCampaignMigrationService;
import kr.jay.migration.application.legacy.keyword.LegacyKeywordMigrationService;
import kr.jay.migration.application.legacy.user.LegacyUserMigrationService;
import kr.jay.migration.application.user.MigrationUserService;
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

    private final MigrationUserService migrationUserService;
    private final LegacyUserMigrationService userMigrationService;
    private final LegacyCampaignMigrationService legacyCampaignMigrationService;
    private final LegacyAdGroupMigrationService legacyAdGroupMigrationService;
    private final LegacyKeywordMigrationService legacyKeywordMigrationService;

    public boolean dispatch(Long userId, Long aggregateId, AggregateType type) {
        if(migrationUserService.isDisagreed(userId)) {
            return false;
        }
        return migrate(userId, aggregateId, type);
    }

    private boolean migrate(Long userId, Long aggregateId, AggregateType type) {
        boolean result = switch (type) {
            case USER -> userMigrationService.migrate(aggregateId);
            case CAMPAIGN -> legacyCampaignMigrationService.migrate(aggregateId);
            case ADGROUP -> legacyAdGroupMigrationService.migrate(aggregateId);
            case KEYWORD -> legacyKeywordMigrationService.migrate(aggregateId);
        };

        if(result) {
            log.info("{}", LegacyMigrationLog.success(userId, type, aggregateId));
        } else {
            log.error("{}", LegacyMigrationLog.fail(userId, type, aggregateId));
        }
        return result;
    }
}
