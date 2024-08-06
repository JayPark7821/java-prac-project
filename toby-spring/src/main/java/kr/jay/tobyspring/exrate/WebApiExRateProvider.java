package kr.jay.tobyspring.exrate;

import java.math.BigDecimal;
import kr.jay.tobyspring.api.ApiTemplate;
import kr.jay.tobyspring.api.ErApiExRateExtractor;
import kr.jay.tobyspring.api.HttpClientApiExecutor;
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

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(
            url,
            new HttpClientApiExecutor(),
            new ErApiExRateExtractor()
        );
    }

}
