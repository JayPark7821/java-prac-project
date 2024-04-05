package kr.jay.orderservice.dto;

/**
 * ProcessPaymentRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record ProcessPaymentRequest(
    Long orderId,
    Long userId,
    Long amountKRW,
    Long paymentMethodId
) {

}
