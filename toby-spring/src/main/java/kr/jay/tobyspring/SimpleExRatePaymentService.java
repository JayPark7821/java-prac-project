package kr.jay.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * SimpleExRatePaymentService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
public class SimpleExRatePaymentService extends PaymentService{

    @Override
    BigDecimal getExRate(String currency) throws IOException {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        } else {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }
}
