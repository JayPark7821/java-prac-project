package kr.jay.orderservice.dto;

/**
 * ProcessDeliveryRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public record ProcessDeliveryRequest(
    Long orderId,
    String productName,
    Long  productCount,
    String address
) {

}
