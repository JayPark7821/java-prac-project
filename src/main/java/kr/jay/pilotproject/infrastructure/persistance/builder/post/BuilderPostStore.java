package kr.jay.pilotproject.infrastructure.persistance.builder.post;

import kr.jay.pilotproject.domain.builder.BuilderPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * PostStore
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Component
@RequiredArgsConstructor
public class BuilderPostStore {

    private final BuilderPostJpaRepository builderPostJpaRepository;

    public BuilderPost save(final BuilderPost builderPost) {
        return builderPostJpaRepository.save(builderPost);
    }
}
