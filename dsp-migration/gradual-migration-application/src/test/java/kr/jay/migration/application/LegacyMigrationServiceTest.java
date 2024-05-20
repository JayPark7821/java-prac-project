package kr.jay.migration.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import kr.jay.migration.domain.legacy.DeletableEntity;
import kr.jay.migration.domain.recent.MigratedEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

/**
 * LegacyMigrationServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
class LegacyMigrationServiceTest {

    @Mock
    LegacyConverter<DeletableEntity, MigratedEntity> converter;
    @Mock
    CrudRepository<DeletableEntity, Long> legacyRepository;
    @Mock
    CrudRepository<MigratedEntity, Long> recentRepository;

    LegacyMigrationService<?, ?> service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new LegacyMigrationService<DeletableEntity, MigratedEntity>(
            converter,
            legacyRepository,
            recentRepository
        ) {
            @Override
            public boolean migrate(Long id) {
                return super.migrate(id);
            }
        };
    }

    @Test
    void 레거시_도메인_조회시_데이터가_없으면_마이그레이션_실패() throws Exception{
        when(legacyRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.migrate(1L);

        assertThat(result).isFalse();
    }

    @Test
    void 레거시_도메인_조회시_데이터가_있고_변환이_잘되어서_마이그레이션_성공() throws Exception{
        when(legacyRepository.findById(1L)).thenReturn(Optional.of(new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return null;
            }

            @Override
            public Long getId() {
                return 1L;
            }
        }));

        when(converter.convert(any())).thenReturn(LocalDateTime::now);
        when(recentRepository.save(any())).thenAnswer(inv->inv.getArgument(0));

        boolean result = service.migrate(1L);

        assertThat(result).isTrue();
    }

    @Test
    void 레거시_도메인이_삭제되었고_마이그레이션된_적이_있으면_삭제() throws Exception{
        when(legacyRepository.findById(1L)).thenReturn(Optional.of(new DeletableEntity() {
            @Override
            public LocalDateTime getDeletedAt() {
                return LocalDateTime.now();
            }

            @Override
            public Long getId() {
                return 1L;
            }
        }));

        when(recentRepository.findById(1L)).thenReturn(Optional.of(()->LocalDateTime.now().minusMinutes(10)));
        boolean result = service.migrate(1L);
        assertAll(() -> assertThat(result).isTrue(),
            () -> verify(recentRepository, times(1)).delete(any()));

    }


    @Test
    void 마이그레이션_중에_RuntimeException이_발생하면_실패() throws Exception{
        service = new LegacyMigrationService<DeletableEntity, MigratedEntity>(
            converter,
            legacyRepository,
            recentRepository
        ) {
            @Override
            public boolean migrate(Long id) {
                return super.migrate(id);
            }

            @Override
            public void migrate(DeletableEntity legacy) {
                throw new RuntimeException();
            }


        };
        boolean result = service.migrate(1L);
        assertThat(result).isFalse();
    }
}