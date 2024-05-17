package kr.jay.legacy.domain.keyword.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.keyword.LegacyKeyword;

public class LegacyKeywordDeletedEvent extends LegacyKeywordEvent {

  public LegacyKeywordDeletedEvent(LegacyKeyword legacyKeyword) {
    super(legacyKeyword);
  }

  @Override
  public LocalDateTime occurredOn() {
    return legacyKeyword.getDeletedAt();
  }

}
