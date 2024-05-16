package kr.jay.legacy.application.user;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.user.LegacyUser;

/**
 * LegacyUserResult
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyUserResult(
    Long id,
    String name,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt

) {

    public static LegacyUserResult from(LegacyUser user) {
        return new LegacyUserResult(
            user.getId(),
            user.getName(),
            user.getCreatedAt(),
            user.getUpdatedAt(),
            user.getDeletedAt()
        );
    }
}
