package kr.jay.legacy.domain.adgroup.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.AggregateType;
import kr.jay.legacy.domain.DomainEvent;
import kr.jay.legacy.domain.adgroup.LegacyAdGroup;

public abstract class LegacyAdGroupEvent implements DomainEvent {

  protected LegacyAdGroup legacyAdGroup;

  LegacyAdGroupEvent(LegacyAdGroup legacyAdGroup) {
    this.legacyAdGroup = legacyAdGroup;
  }

  @Override
  public AggregateType aggregateType() {
    return AggregateType.ADGROUP;
  }

  @Override
  public Long aggregateId() {
    return legacyAdGroup.getId();
  }

  @Override
  public Long ownerId() {
    return legacyAdGroup.getUserId();
  }

}
