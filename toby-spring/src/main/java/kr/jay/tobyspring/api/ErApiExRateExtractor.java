package kr.jay.tobyspring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import kr.jay.tobyspring.exrate.ExRateData;

/**
 * ErApiExRateExtractor
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/4/24
 */
public class ErApiExRateExtractor implements ExRateExtractor{

    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
