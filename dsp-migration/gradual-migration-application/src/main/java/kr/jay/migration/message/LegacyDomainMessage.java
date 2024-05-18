package kr.jay.migration.message;

import java.time.LocalDateTime;
import kr.jay.migration.domain.AggregateType;

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
 
}
