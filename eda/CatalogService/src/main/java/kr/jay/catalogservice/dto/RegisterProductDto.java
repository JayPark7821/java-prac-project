package kr.jay.catalogservice.dto;

import java.util.List;

/**
 * RegisterProductDto
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public record RegisterProductDto(
    Long sellerId,
    String name,
    String description,
    Long price,
    Long stockCount,
    List<String> tags
) {

}