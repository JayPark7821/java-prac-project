package kr.jay.migration.internal.api.user;

import java.time.LocalDateTime;
import kr.jay.migration.domain.migration.user.MigrationUserStatus;

/**
 * MigrationUserResp
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public record MigrationUserResp(Long id, MigrationUserStatus status, LocalDateTime agreedAt, LocalDateTime updatedAt) {

}
