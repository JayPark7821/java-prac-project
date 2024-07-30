package kr.jay.tobyspring.payment;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.jay.tobyspring.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.Test;

/**
 * PaymentServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/30/24
 */
class PaymentServiceTest {

    @Test
    void prepare() throws Exception {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(500)));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualTo(BigDecimal.valueOf(500));
        assertThat(payment.getConvertedAmount()).isEqualTo(BigDecimal.valueOf(5_000));
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

}