package kr.jay.legacy.api.campaign;

import java.time.LocalDateTime;
import kr.jay.legacy.application.campaign.LegacyCampaignResult;

public record LegacyCampaignResp(Long id, String name, Long userId, Long budget,
                                 LocalDateTime createdAt, LocalDateTime updatedAt,
                                 LocalDateTime deletedAt) {

  public static LegacyCampaignResp from(LegacyCampaignResult campaign) {
    return new LegacyCampaignResp(campaign.id(), campaign.name(), campaign.userId(),
        campaign.budget(), campaign.createdAt(), campaign.updatedAt(),
        campaign.deletedAt());
  }
}
