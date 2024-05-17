package kr.jay.legacy.domain.adgroup.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.adgroup.LegacyAdGroup;

public class LegacyAdGroupLinkUrlUpdatedEvent extends LegacyAdGroupEvent {

  public LegacyAdGroupLinkUrlUpdatedEvent(LegacyAdGroup legacyAdGroup) {
    super(legacyAdGroup);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyAdGroup.getUpdatedAt();
  }

}
