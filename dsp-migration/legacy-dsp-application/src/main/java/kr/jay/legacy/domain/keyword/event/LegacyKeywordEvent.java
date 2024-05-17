package kr.jay.legacy.domain.keyword.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.AggregateType;
import kr.jay.legacy.domain.DomainEvent;
import kr.jay.legacy.domain.keyword.LegacyKeyword;

public abstract class LegacyKeywordEvent implements DomainEvent {

    protected LegacyKeyword legacyKeyword;

    protected LegacyKeywordEvent(LegacyKeyword legacyKeyword) {
        this.legacyKeyword = legacyKeyword;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.KEYWORD;
    }

    @Override
    public Long aggregateId() {
        return legacyKeyword.getId();
    }

    @Override
    public Long ownerId() {
        return legacyKeyword.getUserId();
    }

}
