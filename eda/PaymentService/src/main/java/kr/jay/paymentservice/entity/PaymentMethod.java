package kr.jay.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kr.jay.paymentservice.enums.PaymentMethodType;

/**
 * PaymentMethod
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@Entity
@Table(indexes={@Index(name="idx_user_id", columnList="userId")})
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private PaymentMethodType paymentMethodType;
    private String creditCardNumber;

    public PaymentMethod() {
    }

    public PaymentMethod(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
        this.userId = userId;
        this.paymentMethodType = paymentMethodType;
        this.creditCardNumber = creditCardNumber;
    }
}
