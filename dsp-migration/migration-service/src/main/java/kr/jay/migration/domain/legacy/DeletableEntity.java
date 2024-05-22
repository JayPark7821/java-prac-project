package kr.jay.migration.domain.legacy;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * DeletableEntity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
public interface DeletableEntity {

    default boolean isDeleted() {
        return Optional.ofNullable(getDeletedAt()).isPresent();
    }

    LocalDateTime getDeletedAt();

    Long getId();

}
