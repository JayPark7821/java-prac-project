package kr.jay.pilotproject.domain.users;

import java.util.List;
import kr.jay.pilotproject.domain.prod.ProdUser;
import kr.jay.pilotproject.domain.users.command.UserJoinCommand;
import kr.jay.pilotproject.infrastructure.persistance.prod.users.ProdUserReader;
import kr.jay.pilotproject.infrastructure.persistance.prod.users.ProdUserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final ProdUserStore prodUserStore;
    private final ProdUserReader prodUserReader;

    public ProdUser join(final UserJoinCommand command) {
        final ProdUser prodUser = new ProdUser(command.name());
        return prodUserStore.save(prodUser);
    }

    public List<ProdUser> findAllUsers() {
        return prodUserReader.findAll();
    }

    public ProdUser getById(final Long userId) {
        return prodUserReader.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void updateUserName(Long id, String name) {
        prodUserReader.findById(id)
            .ifPresent(user -> {
                user.changeName(name);
            });
    }
}
