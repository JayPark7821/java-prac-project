package kr.jay.pilotproject.interfaces.dto;

import kr.jay.pilotproject.domain.prod.ProdUser;

/**
 * UserResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */
public record UserResponse(
    Long id,
    String name
) {

    public UserResponse(final ProdUser prodUser) {
        this(prodUser.getId(), prodUser.getName());
    }
}
