package kr.jay.deliveryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * UserAddress
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(name = "idx_user_id", columnList = "userId")})
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String address;
    private String alias;

    public UserAddress(Long userId, String address, String alias) {
        this.userId = userId;
        this.address = address;
        this.alias = alias;
    }

}
