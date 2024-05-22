package kr.jay.migration.domain.recent;

import java.time.LocalDateTime;

/**
 * MigratedEntity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
public interface MigratedEntity {

    LocalDateTime getMigratedAt();

}
