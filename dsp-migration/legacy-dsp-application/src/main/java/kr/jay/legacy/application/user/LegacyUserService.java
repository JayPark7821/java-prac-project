package kr.jay.legacy.application.user;

import jakarta.persistence.EntityNotFoundException;
import kr.jay.legacy.domain.user.LegacyUser;
import kr.jay.legacy.domain.user.LegacyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * LegacyUserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
@Service
@RequiredArgsConstructor
public class LegacyUserService {

    private final LegacyUserRepository repository;

    @Transactional
    public LegacyUserResult create(
        LegacyUserCreateCommand command) {
        LegacyUser newUser = LegacyUser.of(command.name());
        return LegacyUserResult.from(repository.save(newUser));
    }

    public LegacyUserResult find(Long id) {
        return LegacyUserResult.from(findById(id));
    }

    private LegacyUser findById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public LegacyUserResult updateName(Long id, String name) {
        LegacyUser user = findById(id);
        user.updateName(name);
        return LegacyUserResult.from(repository.save(user));
    }

    @Transactional
    public LegacyUserResult delete(Long id) {
        LegacyUser user = findById(id);
        user.delete();
        return LegacyUserResult.from(repository.save(user));
    }
}
