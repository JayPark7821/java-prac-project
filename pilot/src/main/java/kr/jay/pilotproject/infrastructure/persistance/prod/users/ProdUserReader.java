package kr.jay.pilotproject.infrastructure.persistance.prod.users;

import java.util.List;
import java.util.Optional;
import kr.jay.pilotproject.domain.prod.ProdUser;
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
public class ProdUserReader {

    private final ProdUserJpaRepository prodUserJpaRepository;

    public Optional<ProdUser> findById(final Long userId) {
        return prodUserJpaRepository.findById(userId);
    }

    public List<ProdUser> findAll() {
        return prodUserJpaRepository.findAll();
    }
}
