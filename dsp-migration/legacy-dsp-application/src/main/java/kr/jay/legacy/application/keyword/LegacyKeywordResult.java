package kr.jay.legacy.application.keyword;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.keyword.LegacyKeyword;

/**
 * LegacyKeywordCreateCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyKeywordResult(Long id, String text, Long adGroupId, Long userId,
                                  LocalDateTime createdAt,
                                  LocalDateTime deletedAt) {

    public static LegacyKeywordResult from(LegacyKeyword keyword) {
        return new LegacyKeywordResult(keyword.getId(), keyword.getText(), keyword.getAdGroupId(),
            keyword.getUserId(), keyword.getCreatedAt(), keyword.getDeletedAt());
    }
}