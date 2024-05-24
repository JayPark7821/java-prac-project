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
    Long aggregateId,
    Long userId
){

    public static LegacyMigrationLog success(Long userId, AggregateType type, Long aggregateId) {
        return new LegacyMigrationLog(true, type, aggregateId,userId);
    }

    public static LegacyMigrationLog fail(Long userId, AggregateType type, Long aggregateId) {
        return new LegacyMigrationLog(false, type, aggregateId, userId);
    }

}
