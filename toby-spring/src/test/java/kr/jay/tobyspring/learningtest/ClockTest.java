package kr.jay.tobyspring.learningtest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ClockTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/1/24
 */
public class ClockTest {

    @Test
    void clock() throws Exception{
        //given
        Clock clock = Clock.systemDefaultZone();

        //when
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        //then
        Assertions.assertThat(dt2).isAfter(dt1);
    }

    @Test
    void fixedClock() throws Exception{
        //given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        //when
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        //then
        Assertions.assertThat(dt2).isEqualTo(dt1);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }

}
