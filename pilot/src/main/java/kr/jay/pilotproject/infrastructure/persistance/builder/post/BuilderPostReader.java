package kr.jay.pilotproject.infrastructure.persistance.builder.post;

import java.util.List;
import kr.jay.pilotproject.domain.builder.BuilderPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * PostReader
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@RequiredArgsConstructor
@Component
public class BuilderPostReader {

    private final BuilderPostJpaRepository builderPostJpaRepository;

    public List<BuilderPost> findAll() {
        return builderPostJpaRepository.findAll();
    }
}
