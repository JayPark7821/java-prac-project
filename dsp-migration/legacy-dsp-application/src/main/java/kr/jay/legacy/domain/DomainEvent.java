package kr.jay.legacy.domain;

import java.time.LocalDateTime;

/**
 * DomainEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public interface DomainEvent {
    AggregateType aggregateType();
    Long aggregateId();
    LocalDateTime occurredOn();
    Long ownerId();

}
