package kr.jay.legacy.domain.campaign.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.campaign.LegacyCampaign;

public class LegacyCampaignDeletedEvent extends LegacyCampaignEvent {

  public LegacyCampaignDeletedEvent(LegacyCampaign legacyCampaign) {
    super(legacyCampaign);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyCampaign.getDeletedAt();
  }

}
