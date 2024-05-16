package kr.jay.legacy.application.campaign;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.campaign.LegacyCampaign;

/**
 * LegacyCampaignResult
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyCampaignResult(Long id, String name, Long userId, Long budget,
                                   LocalDateTime createdAt, LocalDateTime updatedAt,
                                   LocalDateTime deletedAt) {

    public static LegacyCampaignResult from(LegacyCampaign campaign) {
        return new LegacyCampaignResult(campaign.getId(), campaign.getName(), campaign.getUserId(),
            campaign.getBudget(), campaign.getCreatedAt(), campaign.getUpdatedAt(),
            campaign.getDeletedAt());
    }
}
