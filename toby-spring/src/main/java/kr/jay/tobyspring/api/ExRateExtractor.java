package kr.jay.tobyspring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;

/**
 * ExRateExtractor
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/4/24
 */
public interface ExRateExtractor {
    BigDecimal extract(String response) throws JsonProcessingException;

}
