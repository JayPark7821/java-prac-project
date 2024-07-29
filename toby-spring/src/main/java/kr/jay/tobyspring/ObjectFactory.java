package kr.jay.tobyspring;

import kr.jay.tobyspring.exrate.CachedExRateProvider;
import kr.jay.tobyspring.payment.ExRateProvider;
import kr.jay.tobyspring.exrate.WebApiExRateProvider;
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
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }
}
