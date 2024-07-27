package kr.jay.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ObjectFactory
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/26/24
 */
@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }
}
