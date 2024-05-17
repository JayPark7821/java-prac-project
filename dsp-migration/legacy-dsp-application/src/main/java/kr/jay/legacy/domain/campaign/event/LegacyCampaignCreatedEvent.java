package kr.jay.legacy.domain.campaign.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.campaign.LegacyCampaign;

public class LegacyCampaignCreatedEvent extends LegacyCampaignEvent {

  public LegacyCampaignCreatedEvent(LegacyCampaign legacyCampaign) {
    super(legacyCampaign);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyCampaign.getCreatedAt();
  }

}
