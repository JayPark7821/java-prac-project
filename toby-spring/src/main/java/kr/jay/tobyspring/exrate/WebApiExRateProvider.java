package kr.jay.tobyspring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import kr.jay.tobyspring.api.ApiExecutor;
import kr.jay.tobyspring.api.ErApiExRateExtractor;
import kr.jay.tobyspring.api.ExRateExtractor;
import kr.jay.tobyspring.api.SimpleApiExecutor;
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

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(
            url,
            new SimpleApiExecutor(),
            new ErApiExRateExtractor()
        );
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
