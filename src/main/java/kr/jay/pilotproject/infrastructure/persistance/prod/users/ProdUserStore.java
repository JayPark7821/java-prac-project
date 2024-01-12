package kr.jay.pilotproject.infrastructure.persistance.prod.users;

import kr.jay.pilotproject.domain.prod.ProdUser;
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
public class ProdUserStore {

    private final ProdUserJpaRepository prodUserJpaRepository;

    public ProdUser save(final ProdUser prodUser) {
        return prodUserJpaRepository.save(prodUser);
    }
}
