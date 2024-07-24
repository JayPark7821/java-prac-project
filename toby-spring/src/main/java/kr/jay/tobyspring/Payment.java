package kr.jay.tobyspring;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

/**
 * Payment
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
@Getter
@ToString
public class Payment {
    private Long orderId;
    private String currency;
    private BigDecimal foreignCurrencyAmount;
    private BigDecimal exRate;
    private BigDecimal convertedAmount;
    private LocalDateTime validUntil;

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate,
        BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }
}
