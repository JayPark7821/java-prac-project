package kr.jay.migration.application.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import kr.jay.migration.domain.migration.user.MigrationUser;
import kr.jay.migration.domain.migration.user.MigrationUserRepository;
import kr.jay.migration.domain.migration.user.MigrationUserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * MigrationUserServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/24/24
 */
class MigrationUserServiceTest {

    @Mock
    MigrationUserRepository repository;
    @InjectMocks
    MigrationUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 사용자가_마이그레이션에_동의함() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(inv->inv.getArgument(0));

        LocalDateTime before = LocalDateTime.now();
        MigrationUserResult result = service.agree(1L);
        LocalDateTime after = LocalDateTime.now();

        assertAll(()->assertThat(result.id()).isEqualTo(1L),
                  ()->assertThat(result.status()).isEqualTo(MigrationUserStatus.AGREED),
                  ()->assertThat(result.agreedAt()).isBetween(before, after),
                  ()->assertThat(result.updatedAt()).isBetween(before, after));
    }

    @Test
    void 이미_마이그레이션에_동의했으면_에러() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        assertThatThrownBy(()->service.agree(1L))
            .isInstanceOf(AlreadyAgreedException.class)
            .hasMessage("User 1 already agreed");
    }

    @Test
    void 존재하지_않으면_에러() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(()->service.findById(1L))
            .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 동의하지_않으면_true() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.empty());

        boolean result = service.isDisagreed(1L);

        assertThat(result).isTrue();
    }

    @Test
    void 동의했으면_false() throws Exception{
        when(repository.findById(1L)).thenReturn(Optional.of(MigrationUser.agreed(1L)));

        boolean result = service.isDisagreed(1L);

        assertThat(result).isFalse();
    }
}