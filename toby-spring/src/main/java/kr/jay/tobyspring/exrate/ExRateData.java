package kr.jay.tobyspring.exrate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

/**
 * ExRateData
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}