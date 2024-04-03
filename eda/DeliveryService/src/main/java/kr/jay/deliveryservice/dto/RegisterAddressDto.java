package kr.jay.deliveryservice.dto;

import lombok.Getter;

/**
 * RegisterAddressDto
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */

public record RegisterAddressDto(
    Long userId,
    String address,
    String alias
) {

}
