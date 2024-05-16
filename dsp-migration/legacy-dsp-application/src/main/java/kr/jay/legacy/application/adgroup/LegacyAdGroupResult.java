package kr.jay.legacy.application.adgroup;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.adgroup.LegacyAdGroup;

/**
 * LegacyAdGroupResult
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyAdGroupResult(Long id, String name, Long campaignId, Long userId,
                                  String linkUrl, LocalDateTime createdAt,
                                  LocalDateTime updatedAt, LocalDateTime deletedAt) {

    public static LegacyAdGroupResult from(LegacyAdGroup adGroup) {
        return new LegacyAdGroupResult(adGroup.getId(), adGroup.getName(), adGroup.getCampaignId(),
            adGroup.getUserId(), adGroup.getLinkUrl(), adGroup.getCreatedAt(),
            adGroup.getUpdatedAt(), adGroup.getDeletedAt());
    }
}