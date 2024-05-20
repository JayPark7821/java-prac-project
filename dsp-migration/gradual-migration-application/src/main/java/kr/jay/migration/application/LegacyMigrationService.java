package kr.jay.migration.application;

import jakarta.persistence.EntityNotFoundException;
import kr.jay.migration.domain.legacy.DeletableEntity;
import kr.jay.migration.domain.recent.MigratedEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

/**
 * LegacyMigrationService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
@Slf4j
public abstract class LegacyMigrationService<Legacy extends DeletableEntity, Recent extends MigratedEntity> implements
    MigrationService {

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
        try {
            migrate(findLegacy(id));
            return true;
        } catch (RuntimeException e) {
            log.error("migration error", e);
            return false;
        }
    }

    protected void migrate(Legacy legacy) {
        if(legacy.isDeleted()){
            recentRepository.findById(legacy.getId())
                .ifPresent(recentRepository::delete);
        }else{
            saveRecent(getConvert(legacy));
        }
    }

    private void saveRecent(Recent recent) {
        recentRepository.save(recent);
    }

    private Recent getConvert(Legacy legacy) {
        return converter.convert(legacy);
    }

    private Legacy findLegacy(Long id) {
        return legacyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
