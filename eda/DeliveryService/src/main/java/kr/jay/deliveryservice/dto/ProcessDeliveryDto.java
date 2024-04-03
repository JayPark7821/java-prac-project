package kr.jay.deliveryservice.dto;

/**
 * ProcessDeliveryDto
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public record ProcessDeliveryDto(
    Long orderId,
    String productName,
    Long productCount,
    String address
) {

}
