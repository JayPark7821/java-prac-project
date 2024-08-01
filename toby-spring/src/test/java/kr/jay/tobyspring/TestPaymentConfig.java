package kr.jay.tobyspring;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import kr.jay.tobyspring.payment.ExRateProvider;
import kr.jay.tobyspring.payment.ExRateProviderStub;
import kr.jay.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TestPaymentConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/26/24
 */
@Configuration
@ComponentScan
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock clock(){
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
