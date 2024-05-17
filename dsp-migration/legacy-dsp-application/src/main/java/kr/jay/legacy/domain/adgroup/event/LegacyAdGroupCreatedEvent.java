package kr.jay.legacy.domain.adgroup.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.adgroup.LegacyAdGroup;

public class LegacyAdGroupCreatedEvent extends LegacyAdGroupEvent {

  public LegacyAdGroupCreatedEvent(LegacyAdGroup legacyAdGroup) {
    super(legacyAdGroup);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyAdGroup.getCreatedAt();
  }

}
