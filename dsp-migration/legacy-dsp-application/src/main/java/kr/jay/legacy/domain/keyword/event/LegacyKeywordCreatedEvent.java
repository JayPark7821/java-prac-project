package kr.jay.legacy.domain.keyword.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.keyword.LegacyKeyword;

public class LegacyKeywordCreatedEvent extends LegacyKeywordEvent {

  public LegacyKeywordCreatedEvent(LegacyKeyword legacyKeyword) {
    super(legacyKeyword);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyKeyword.getCreatedAt();
  }

}
