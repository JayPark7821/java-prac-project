package kr.jay.migration.application;

import kr.jay.migration.domain.legacy.DeletableEntity;
import kr.jay.migration.domain.recent.MigratedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * LegacyMigrationService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
public abstract class LegacyMigrationService<Legacy extends DeletableEntity, Recent extends MigratedEntity> implements MigrationService{

    protected LegacyConverter<Legacy, Recent> converter;
    protected CrudRepository<Legacy, Long> legacyRepository;
    protected CrudRepository<Recent, Long> recentRepository;

    public LegacyMigrationService(
        LegacyConverter<Legacy, Recent> converter,
        CrudRepository<Legacy, Long> legacyRepository,
        CrudRepository<Recent, Long> recentRepository
    ) {
        this.converter = converter;
        this.legacyRepository = legacyRepository;
        this.recentRepository = recentRepository;
    }

    @Override
    public boolean migrate(Long id) {
        return false ;
    }
}
