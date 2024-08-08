package kr.jay.tobyspring.exrate;

import java.math.BigDecimal;
import kr.jay.tobyspring.payment.ExRateProvider;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateExRateProvicer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/8/24
 */
public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }
}
