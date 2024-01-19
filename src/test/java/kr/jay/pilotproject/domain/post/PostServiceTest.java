package kr.jay.pilotproject.domain.post;

import kr.jay.pilotproject.domain.post.command.PostCreateCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * PostServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/15/24
 */

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService sut;


    @Test
    void test() throws Exception {
        //given

        sut.save(new PostCreateCommand(1L, "title", "content"));

        //when

        //then

    }
}