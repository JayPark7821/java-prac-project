package kr.jay.tobyspring.payment;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * ExRateProvider
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/25/24
 */
public interface ExRateProvider {
    BigDecimal getExRate(String currency);

}
