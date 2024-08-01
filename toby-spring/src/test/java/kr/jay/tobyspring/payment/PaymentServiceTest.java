package kr.jay.tobyspring.payment;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PaymentServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/30/24
 */
class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void prepare() throws Exception {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), this.clock);
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000), this.clock);
        testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() throws Exception {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime validUntil = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(validUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock)
        throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}