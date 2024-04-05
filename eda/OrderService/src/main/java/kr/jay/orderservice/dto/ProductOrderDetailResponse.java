package kr.jay.orderservice.dto;

import kr.jay.orderservice.enums.OrderStatus;

/**
 * ProductOrderDetailResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record ProductOrderDetailResponse(
    Long id,
    Long userId,
    Long productId,
    Long paymentId,
    Long deliveryId,
    OrderStatus orderStatus,
    String paymentStatus,
    String deliveryStatus
) {

}
