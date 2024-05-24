package kr.jay.migration.application.dispatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import kr.jay.migration.application.legacy.adgroup.LegacyAdGroupMigrationService;
import kr.jay.migration.application.legacy.campaign.LegacyCampaignMigrationService;
import kr.jay.migration.application.legacy.keyword.LegacyKeywordMigrationService;
import kr.jay.migration.application.legacy.user.LegacyUserMigrationService;
import kr.jay.migration.application.user.MigrationUserService;
import kr.jay.migration.domain.AggregateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * MigrationDispatcherTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/24/24
 */
class MigrationDispatcherTest {

    @Mock
    MigrationUserService migrationUserService;
    @Mock
    LegacyUserMigrationService userMigrationService;
    @Mock
    LegacyCampaignMigrationService legacyCampaignMigrationService;
    @Mock
    LegacyAdGroupMigrationService legacyAdGroupMigrationService;
    @Mock
    LegacyKeywordMigrationService legacyKeywordMigrationService;

    @InjectMocks
    MigrationDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void 사용자가_마이그레이션에_동의하지_않았으면_dispatch_false(boolean migrationSuccess) throws Exception{
        when(migrationUserService.isDisagreed(1L)).thenReturn(true);
        when(legacyAdGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isFalse();
    }

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void 사용자가_마이그레이션에_동의했으면_마이그레이션_결과를_리턴(boolean migrationSuccess) throws Exception{
        when(migrationUserService.isDisagreed(1L)).thenReturn(false);
        when(legacyAdGroupMigrationService.migrate(1L)).thenReturn(migrationSuccess);

        boolean result = dispatcher.dispatch(1L, 1L, AggregateType.ADGROUP);

        assertThat(result).isEqualTo(migrationSuccess);
    }
}