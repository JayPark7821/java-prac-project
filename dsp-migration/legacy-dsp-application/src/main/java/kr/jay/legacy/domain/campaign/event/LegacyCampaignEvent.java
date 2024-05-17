package kr.jay.legacy.domain.campaign.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.AggregateType;
import kr.jay.legacy.domain.DomainEvent;
import kr.jay.legacy.domain.campaign.LegacyCampaign;

public abstract class LegacyCampaignEvent implements DomainEvent {

  protected LegacyCampaign legacyCampaign;

  protected LegacyCampaignEvent(LegacyCampaign legacyCampaign) {
    this.legacyCampaign = legacyCampaign;
  }

  @Override
  public AggregateType aggregateType() {
    return AggregateType.CAMPAIGN;
  }

  @Override
  public Long aggregateId() {
    return legacyCampaign.getId();
  }

  @Override
  public Long ownerId() {
    return legacyCampaign.getUserId();
  }

}
