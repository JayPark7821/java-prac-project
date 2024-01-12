package kr.jay.pilotproject.infrastructure.persistance.prod.post;

import java.util.List;
import kr.jay.pilotproject.domain.prod.ProdPost;
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
public class ProdPostReader {

    private final ProdPostJpaRepository prodPostJpaRepository;

    public List<ProdPost> findAll() {
        return prodPostJpaRepository.findAll();
    }
}
