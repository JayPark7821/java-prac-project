package kr.jay.pilotproject;

import kr.jay.pilotproject.domain.post.PostService;
import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DistributedTransactionTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/12/24
 */

@SpringBootTest
class DistributedTransactionTest {

    @Autowired
    private PostService sut;

    @Test
    void 분산트랜잭션_test() throws Exception {
        //given

        //when
        sut.saveWithDistributedTransaction(new PostCreateCommand(1L, "title", "testtttttttt"));
        //then

    }
}
