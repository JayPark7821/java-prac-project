package kr.jay.orderservice.dto;

import java.util.Map;

/**
 * StartOrderResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record StartOrderResponse(
    Long orderId,
    Map<String, Object> paymentMethod,
    Map<String, Object> address
) {

}
