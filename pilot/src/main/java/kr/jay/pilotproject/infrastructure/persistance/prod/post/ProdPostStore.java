package kr.jay.pilotproject.infrastructure.persistance.prod.post;

import kr.jay.pilotproject.domain.prod.ProdPost;
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
public class ProdPostStore {

    private final ProdPostJpaRepository prodPostJpaRepository;

    public ProdPost save(final ProdPost prodPost) {
        return prodPostJpaRepository.save(prodPost);
    }
}
