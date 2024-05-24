package kr.jay.migration.application.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.jay.migration.domain.migration.user.MigrationUser;
import kr.jay.migration.domain.migration.user.MigrationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * MigrationUserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
@Service
@RequiredArgsConstructor
public class MigrationUserService {

    private final MigrationUserRepository migrationUserRepository;

    @Transactional
    public MigrationUserResult agree(Long userId) {
        migrationUserRepository.findById(userId)
            .ifPresent(user -> {
                throw new AlreadyAgreedException(String.format("User %d already agreed", userId));
            });
        return MigrationUserResult.from(migrationUserRepository.save(MigrationUser.agreed(userId)));
    }

    public MigrationUserResult findById(Long userId) {
        return MigrationUserResult.from(
            migrationUserRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new)
        );
    }

    public boolean isDisagreed(Long userId) {
        return migrationUserRepository.findById(userId).isEmpty();
    }
}
