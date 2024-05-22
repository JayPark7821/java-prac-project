package kr.jay.migration.application;

import kr.jay.migration.domain.legacy.DeletableEntity;
import kr.jay.migration.domain.recent.MigratedEntity;

/**
 * LegacyConverter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {

    Recent convert(Legacy legacy);

}
