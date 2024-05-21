package kr.jay.migration.application.legacy.user;

import kr.jay.migration.application.LegacyConverter;
import kr.jay.migration.domain.legacy.user.LegacyUser;
import kr.jay.migration.domain.recent.user.RecentUser;
import org.springframework.stereotype.Component;

/**
 * LegacyUserConverter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Component
public class LegacyUserConverter implements LegacyConverter<LegacyUser, RecentUser> {


    @Override
    public RecentUser convert(LegacyUser legacyUser) {
        return RecentUser.migrated(
            legacyUser.getId(),
            legacyUser.getName(),
            legacyUser.getCreatedAt(),
            legacyUser.getUpdatedAt(),
            legacyUser.getDeletedAt()
        );
    }
}
