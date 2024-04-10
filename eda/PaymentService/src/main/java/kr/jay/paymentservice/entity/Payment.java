package kr.jay.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.jay.paymentservice.enums.PaymentMethodType;
import kr.jay.paymentservice.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Payment
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@Getter
@NoArgsConstructor
@Entity
@Table(indexes={@Index(name="idx_user_id", columnList="userId")})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long userId;
    private Long orderId;
    private Long amountKRW;
    private PaymentMethodType paymentMethodType;
    private String paymentData;
    private PaymentStatus paymentStatus;
    @Column(unique = true)
    private Long referenceCode;


    public Payment(Long userId, Long orderId, Long amountKRW, PaymentMethodType paymentMethodType,
        String paymentData,
        PaymentStatus paymentStatus, Long referenceCode) {
        this.userId = userId;
        this.orderId = orderId;
        this.amountKRW = amountKRW;
        this.paymentMethodType = paymentMethodType;
        this.paymentData = paymentData;
        this.paymentStatus = paymentStatus;
        this.referenceCode = referenceCode;
    }
}
