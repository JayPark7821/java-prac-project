package kr.jay.tobyspring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.jay.tobyspring.payment.ExRateProvider;

/**
 * CachedExRateProvider
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/28/24
 */
public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cacheValidTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency)   {
        if( cachedExRate == null || cacheValidTime.isAfter(LocalDateTime.now())){
            cachedExRate = target.getExRate(currency);
            cacheValidTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache updated");
        }
        return cachedExRate;
    }
}
