package kr.jay.orderservice.dto;

/**
 * StartOrderRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record StartOrderRequest(
    Long userId,
    Long productId,
    Long count
) {

}
