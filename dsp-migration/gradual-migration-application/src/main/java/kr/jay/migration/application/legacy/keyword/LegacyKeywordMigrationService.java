package kr.jay.migration.application.legacy.keyword;

import kr.jay.migration.application.LegacyConverter;
import kr.jay.migration.application.LegacyMigrationService;
import kr.jay.migration.application.legacy.user.LegacyUserConverter;
import kr.jay.migration.domain.legacy.keyword.LegacyKeyword;
import kr.jay.migration.domain.legacy.keyword.LegacyKeywordRepository;
import kr.jay.migration.domain.legacy.user.LegacyUser;
import kr.jay.migration.domain.legacy.user.LegacyUserRepository;
import kr.jay.migration.domain.recent.keyword.RecentKeyword;
import kr.jay.migration.domain.recent.keyword.RecentKeywordRepository;
import kr.jay.migration.domain.recent.user.RecentUser;
import kr.jay.migration.domain.recent.user.RecentUserRepository;
import org.springframework.stereotype.Service;

/**
 * LegacyKeywordMigrationService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Service
public class LegacyKeywordMigrationService extends LegacyMigrationService<LegacyKeyword, RecentKeyword> {

    public LegacyKeywordMigrationService(
        LegacyKeywordConverter converter,
        LegacyKeywordRepository legacyRepository,
        RecentKeywordRepository recentRepository
    ) {
        super(converter, legacyRepository, recentRepository);
    }
}
