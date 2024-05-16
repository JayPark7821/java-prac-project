package kr.jay.pilotproject.infrastructure.persistance.builder.users;

import kr.jay.pilotproject.domain.builder.BuilderUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * UserStore
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class BuilderUserStore {

    private final BuilderUserJpaRepository builderUserJpaRepository;

    public BuilderUser save(final BuilderUser builderUser) {
        return builderUserJpaRepository.save(builderUser);
    }
}
