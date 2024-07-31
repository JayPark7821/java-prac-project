package kr.jay.tobyspring.payment;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.math.BigDecimal;
import kr.jay.tobyspring.ObjectFactory;
import kr.jay.tobyspring.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * PaymentServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/30/24
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;
    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    void prepare() throws Exception {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment2.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));


    }
}