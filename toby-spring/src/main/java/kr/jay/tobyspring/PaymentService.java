package kr.jay.tobyspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PaymentService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
abstract public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = getExRate(currency);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;

}
