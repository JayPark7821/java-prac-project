package kr.jay.migration.application.legacy.keyword;

import kr.jay.migration.application.LegacyConverter;
import kr.jay.migration.domain.legacy.keyword.LegacyKeyword;
import kr.jay.migration.domain.recent.keyword.RecentKeyword;
import org.springframework.stereotype.Component;

/**
 * LegacyKeywordConverter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Component
public class LegacyKeywordConverter implements LegacyConverter<LegacyKeyword, RecentKeyword> {

    @Override
    public RecentKeyword convert(LegacyKeyword legacyKeyword) {
        return RecentKeyword.migrated(
            legacyKeyword.getId(),
            legacyKeyword.getText(),
            legacyKeyword.getAdGroupId(),
            legacyKeyword.getUserId(),
            legacyKeyword.getCreatedAt(),
            legacyKeyword.getDeletedAt()
        );
    }
}
