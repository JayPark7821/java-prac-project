package kr.jay.pilotproject.domain.post;

import java.util.List;
import kr.jay.pilotproject.common.config.multidatasource.DataSourceContextHolder;
import kr.jay.pilotproject.common.config.multidatasource.EdcDataSource;
import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import kr.jay.pilotproject.domain.users.User;
import kr.jay.pilotproject.domain.users.UserService;
import kr.jay.pilotproject.infrastructure.persistance.post.PostReader;
import kr.jay.pilotproject.infrastructure.persistance.post.PostStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * PostService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostStore postStore;
    private final PostReader postReader;
    private final UserService userService;

    public List<Post> findAllPosts() {
        return postReader.findAll();
    }

    public void save(final PostCreateCommand command) {

        DataSourceContextHolder.setDataSource(EdcDataSource.PROD);
        final User user = userService.getById(command.userId());

        for (EdcDataSource ds : EdcDataSource.values()) {
            log.info("===================== ds: {}", ds);
            DataSourceContextHolder.setDataSource(ds);
            postStore.save(new Post(command.title(), command.content(), user));
        }

        DataSourceContextHolder.clear();
    }

}
