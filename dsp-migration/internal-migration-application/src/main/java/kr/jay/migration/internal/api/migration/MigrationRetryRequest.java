package kr.jay.migration.internal.api.migration;

import kr.jay.migration.domain.AggregateType;

/**
 * MigrationRetryRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/25/24
 */
public record MigrationRetryRequest(
    Long userId, Long aggregateId, AggregateType aggregateType
) {

}
