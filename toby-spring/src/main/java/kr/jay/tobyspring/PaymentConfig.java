package kr.jay.tobyspring;

import java.time.Clock;
import kr.jay.tobyspring.api.ApiTemplate;
import kr.jay.tobyspring.api.ErApiExRateExtractor;
import kr.jay.tobyspring.api.SimpleApiExecutor;
import kr.jay.tobyspring.exrate.RestTemplateExRateProvider;
import kr.jay.tobyspring.payment.ExRateProvider;
import kr.jay.tobyspring.exrate.WebApiExRateProvider;
import kr.jay.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * PaymentConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/26/24
 */
@Configuration
@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }
}
