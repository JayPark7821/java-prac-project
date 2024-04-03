package kr.jay.deliveryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.jay.deliveryservice.enums.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Delivery
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(name = "idx_order_id", columnList = "orderId")})
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String productName;
    private Long productCount;
    private String address;
    private Long referenceCode;
    private DeliveryStatus status;


    public Delivery(Long orderId, String productName, Long productCount, String address, DeliveryStatus status, Long referenceCode) {
        this.orderId = orderId;
        this.productName = productName;
        this.productCount = productCount;
        this.address = address;
        this.status = status;
        this.referenceCode = referenceCode;
    }

    public void setStatus(DeliveryStatus deliveryStatus) {
        this.status = deliveryStatus;
    }
}
