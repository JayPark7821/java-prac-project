package kr.jay.tobyspring.payment;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * PaymentTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/2/24
 */
class PaymentTest {
    @Test
    void createPrepared() throws Exception{
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN,
        BigDecimal.valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() throws Exception{
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN,
            BigDecimal.valueOf(1_000), LocalDateTime.now(clock));

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }
}
