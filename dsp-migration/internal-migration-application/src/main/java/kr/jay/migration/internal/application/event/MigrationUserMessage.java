package kr.jay.migration.internal.application.event;

import kr.jay.migration.domain.migration.user.MigrationUserAgreedEvent;
import kr.jay.migration.domain.migration.user.MigrationUserEvent;
import kr.jay.migration.domain.migration.user.MigrationUserStatus;

/**
 * MigrationUserMessage
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/26/24
 */
public record MigrationUserMessage(
    Long userId,
    MigrationUserStatus status
) {

    public static MigrationUserMessage from(MigrationUserEvent event) {
        return new MigrationUserMessage(event.getUserId(), event.getStatus());
    }
}
