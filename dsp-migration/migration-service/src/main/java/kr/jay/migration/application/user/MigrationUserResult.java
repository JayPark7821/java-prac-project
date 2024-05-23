package kr.jay.migration.application.user;

import java.time.LocalDateTime;
import kr.jay.migration.domain.migration.user.MigrationUser;
import kr.jay.migration.domain.migration.user.MigrationUserEvent;
import kr.jay.migration.domain.migration.user.MigrationUserStatus;

/**
 * MigrationUserResult
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public record MigrationUserResult(
    Long id,
    MigrationUserStatus status,
    LocalDateTime agreedAt,
    LocalDateTime updatedAt
) {

    public static MigrationUserResult from(MigrationUser user) {
        return new MigrationUserResult(user.getId(), user.getStatus(), user.getAgreedAt(), user.getUpdatedAt());
    }

}
