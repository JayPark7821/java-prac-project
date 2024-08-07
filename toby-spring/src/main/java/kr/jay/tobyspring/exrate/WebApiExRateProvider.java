package kr.jay.tobyspring.exrate;

import java.math.BigDecimal;
import kr.jay.tobyspring.api.ApiTemplate;
import kr.jay.tobyspring.payment.ExRateProvider;

/**
 * WebApiExRatePaymentService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url);
    }

}
