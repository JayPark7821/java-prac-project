package kr.jay.pilotproject.domain.post;

import java.util.List;
import kr.jay.pilotproject.domain.builder.BuilderPost;
import kr.jay.pilotproject.domain.builder.BuilderUser;
import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import kr.jay.pilotproject.domain.prod.ProdPost;
import kr.jay.pilotproject.domain.prod.ProdUser;
import kr.jay.pilotproject.infrastructure.persistance.builder.post.BuilderPostReader;
import kr.jay.pilotproject.infrastructure.persistance.builder.post.BuilderPostStore;
import kr.jay.pilotproject.infrastructure.persistance.builder.users.BuilderUserReader;
import kr.jay.pilotproject.infrastructure.persistance.builder.users.BuilderUserStore;
import kr.jay.pilotproject.infrastructure.persistance.prod.post.ProdPostReader;
import kr.jay.pilotproject.infrastructure.persistance.prod.post.ProdPostStore;
import kr.jay.pilotproject.infrastructure.persistance.prod.users.ProdUserReader;
import kr.jay.pilotproject.infrastructure.persistance.prod.users.ProdUserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PostService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */

@RequiredArgsConstructor
@Service
public class PostService {

    private final BuilderPostStore builderPostStore;
    private final ProdPostStore prodPostStore;
    private final BuilderPostReader builderPostReader;
    private final ProdPostReader prodPostReader;

    private final BuilderUserStore builderUserstore;
    private final ProdUserStore prodUserStore;
    private final BuilderUserReader builderUserReader;
    private final ProdUserReader prodUserReader;

    public List<BuilderPost> findAllPosts() {
        return builderPostReader.findAll();
    }

    public BuilderPost save(final PostCreateCommand command) {
        BuilderUser user = builderUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        return builderPostStore.save(new BuilderPost(command.title(), command.content(), user));
    }


    @Transactional(transactionManager = "multiTxManager")
    public void saveWithTransaction(final PostCreateCommand command) {
        BuilderUser builderUser = builderUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        builderPostStore.save(new BuilderPost(command.title(), command.content(), builderUser));

        builderUser.changeName("changed name");

        ProdUser prodUser = prodUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        prodPostStore.save(new ProdPost(command.title(), command.content(), prodUser));
    }

    @Transactional(transactionManager = "multiTxManager")
    public void rollBack(final PostCreateCommand command) {
        BuilderUser builderUser = builderUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        builderPostStore.save(new BuilderPost(command.title(), command.content(), builderUser));

        builderUser.changeName("changed name");

        ProdUser prodUser = prodUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        prodPostStore.save(new ProdPost(command.title(), command.content(), prodUser));

        throw new RuntimeException("test");
    }

    @Transactional
    public void noTXManagerSave(final PostCreateCommand command) {
        BuilderUser builderUser = builderUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        builderPostStore.save(new BuilderPost(command.title(), command.content(), builderUser));

        builderUser.changeName("changed name");

        ProdUser prodUser = prodUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        prodPostStore.save(new ProdPost(command.title(), command.content(), prodUser));
    }

    @Transactional
    public void noTXManagerRollBack(final PostCreateCommand command) {
        BuilderUser builderUser = builderUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        builderPostStore.save(new BuilderPost(command.title(), command.content(), builderUser));

        builderUser.changeName("changed name");

        ProdUser prodUser = prodUserReader.findById(command.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        prodPostStore.save(new ProdPost(command.title(), command.content(), prodUser));

        throw new RuntimeException("test");
    }

}
