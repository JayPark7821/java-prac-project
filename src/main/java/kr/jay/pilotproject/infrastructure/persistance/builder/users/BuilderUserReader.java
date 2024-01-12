package kr.jay.pilotproject.infrastructure.persistance.builder.users;

import java.util.List;
import java.util.Optional;
import kr.jay.pilotproject.domain.builder.BuilderUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * UserReader
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class BuilderUserReader {

    private final BuilderUserJpaRepository builderUserJpaRepository;

    public Optional<BuilderUser> findById(final Long userId) {
        return builderUserJpaRepository.findById(userId);
    }

    public List<BuilderUser> findAll() {
        return builderUserJpaRepository.findAll();
    }
}
