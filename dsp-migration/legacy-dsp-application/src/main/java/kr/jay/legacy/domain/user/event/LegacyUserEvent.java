package kr.jay.legacy.domain.user.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.AggregateType;
import kr.jay.legacy.domain.DomainEvent;
import kr.jay.legacy.domain.user.LegacyUser;

/**
 * LegacyUserEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public abstract class LegacyUserEvent implements DomainEvent {

    protected LegacyUser legacyUser;

    protected LegacyUserEvent(LegacyUser legacyUser) {
        this.legacyUser = legacyUser;
    }

    @Override
    public AggregateType aggregateType() {
        return AggregateType.USER;
    }

    @Override
    public Long aggregateId() {
        return legacyUser.getId();
    }

    @Override
    public Long ownerId() {
        return legacyUser.getId();
    }
}
