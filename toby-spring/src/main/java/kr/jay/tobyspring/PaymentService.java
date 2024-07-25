package kr.jay.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * PaymentService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
public class PaymentService {

    private final WebApiExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new WebApiExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = exRateProvider.getWebExRate(currency);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}
