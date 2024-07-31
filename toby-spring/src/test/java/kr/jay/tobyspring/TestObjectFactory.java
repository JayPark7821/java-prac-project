package kr.jay.tobyspring;

import java.math.BigDecimal;
import kr.jay.tobyspring.exrate.WebApiExRateProvider;
import kr.jay.tobyspring.payment.ExRateProvider;
import kr.jay.tobyspring.payment.ExRateProviderStub;
import kr.jay.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ObjectFactory
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/26/24
 */
@Configuration
@ComponentScan
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }


    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
