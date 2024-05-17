package kr.jay.legacy.application.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.AggregateType;
import kr.jay.legacy.domain.DomainEvent;

/**
 * LegacyDomainMessage
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/17/24
 */
public record LegacyDomainMessage(
    AggregateType aggregateType,
    Long aggregateId,
    Long ownerId,
    LocalDateTime occurredOn
) {

    public static LegacyDomainMessage from(DomainEvent event) {
        return new LegacyDomainMessage(
            event.aggregateType(),
            event.aggregateId(),
            event.ownerId(),
            event.occurredOn()
        );
    }
}
