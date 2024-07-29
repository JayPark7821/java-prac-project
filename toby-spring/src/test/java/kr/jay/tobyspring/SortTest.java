package kr.jay.tobyspring;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * SortTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/29/24
 */
class SortTest {

    Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    void sort2Items() throws Exception{
        //given

        //when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "a"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "aa"));
    }

    @Test
    void sort3Items() throws Exception{
        //given
        //when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "a", "aaa"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "aa", "aaa"));
    }

    @Test
    void sortAlreadySorted() throws Exception{
        //given

        //when
        List<String> list = sort.sortByLength(Arrays.asList("a", "aa", "aaa"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "aa", "aaa"));
    }
}