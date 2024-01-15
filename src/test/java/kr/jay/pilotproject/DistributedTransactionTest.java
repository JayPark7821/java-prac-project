package kr.jay.pilotproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import kr.jay.pilotproject.domain.post.PostService;
import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import kr.jay.pilotproject.infrastructure.persistance.builder.post.BuilderPostReader;
import kr.jay.pilotproject.infrastructure.persistance.prod.post.ProdPostReader;
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

    @Autowired
    private ProdPostReader prodPostReader;
    @Autowired
    private BuilderPostReader builderPostReader;

    @Test
    void 분산트랜잭션_test() throws Exception {
        //given
        int prodSize = prodPostReader.findAll().size();
        int builderSize = builderPostReader.findAll().size();

        //when
        sut.saveWithTransaction(new PostCreateCommand(1L, "title", "testtttttttt"));
        //then

        assertThat(prodPostReader.findAll().size()).isEqualTo(prodSize + 1);
        assertThat(builderPostReader.findAll().size()).isEqualTo(builderSize + 1);
    }

    @Test
    void 분산트랜잭션_test2() throws Exception {
        //given
        int prodSize = prodPostReader.findAll().size();
        int builderSize = builderPostReader.findAll().size();

        //when
        assertThatThrownBy(() -> sut.rollBack(new PostCreateCommand(1L, "title", "testtttttttt")));
        //then

        assertThat(prodPostReader.findAll().size()).isEqualTo(prodSize);
        assertThat(builderPostReader.findAll().size()).isEqualTo(builderSize);
    }


    @Test
    void 분산트랜잭션_test3() throws Exception {
        //given
        int prodSize = prodPostReader.findAll().size();
        int builderSize = builderPostReader.findAll().size();

        //when
        sut.noTXManagerSave(new PostCreateCommand(1L, "title", "testtttttttt"));
        //then

        assertThat(prodPostReader.findAll().size()).isEqualTo(prodSize + 1);
        assertThat(builderPostReader.findAll().size()).isEqualTo(builderSize + 1);
    }

    @Test
    void 분산트랜잭션_test4() throws Exception {
        //given
        int prodSize = prodPostReader.findAll().size();
        int builderSize = builderPostReader.findAll().size();

        //when
        assertThatThrownBy(() -> sut.noTXManagerRollBack(new PostCreateCommand(1L, "title", "testtttttttt")));
        //then

        assertThat(prodPostReader.findAll().size()).isEqualTo(prodSize);
        assertThat(builderPostReader.findAll().size()).isEqualTo(builderSize);
    }


}
