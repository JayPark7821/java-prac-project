package kr.jay.legacy.domain.adgroup.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.adgroup.LegacyAdGroup;

public class LegacyAdGroupDeletedEvent extends LegacyAdGroupEvent {

  public LegacyAdGroupDeletedEvent(LegacyAdGroup legacyAdGroup) {
    super(legacyAdGroup);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyAdGroup.getDeletedAt();
  }

}
