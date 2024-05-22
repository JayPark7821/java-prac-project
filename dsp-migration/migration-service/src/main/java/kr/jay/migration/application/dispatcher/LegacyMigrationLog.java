package kr.jay.migration.application.dispatcher;

import kr.jay.migration.domain.AggregateType;

/**
 * LegacyMigrationLog
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
public record LegacyMigrationLog(
    boolean success,
    AggregateType type,
    Long aggregateId
){

    public static LegacyMigrationLog success(AggregateType type, Long aggregateId) {
        return new LegacyMigrationLog(true, type, aggregateId);
    }

    public static LegacyMigrationLog fail(AggregateType type, Long aggregateId) {
        return new LegacyMigrationLog(false, type, aggregateId);
    }

}
