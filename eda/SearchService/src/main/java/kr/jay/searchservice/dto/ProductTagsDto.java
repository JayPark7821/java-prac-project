package kr.jay.searchservice.dto;

import java.util.List;

/**
 * ProductTagsDto
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public record ProductTagsDto(
    Long productId,
    List<String> tags
) {

}
