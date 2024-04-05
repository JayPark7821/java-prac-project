package kr.jay.orderservice.dto;

/**
 * FinishOrderRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record FinishOrderRequest(
    Long orderId,
    Long paymentMethodId,
    Long addressId

) {

}
