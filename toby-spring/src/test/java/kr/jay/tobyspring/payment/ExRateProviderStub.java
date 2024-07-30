package kr.jay.tobyspring.payment;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * ExRateProviderStub
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/30/24
 */
public class ExRateProviderStub implements ExRateProvider{

    private BigDecimal exRate;

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public ExRateProviderStub(BigDecimal exRate) {
        this.exRate = exRate;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        return exRate;
    }
}
