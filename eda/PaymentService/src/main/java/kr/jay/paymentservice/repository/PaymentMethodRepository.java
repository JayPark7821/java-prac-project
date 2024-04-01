package kr.jay.paymentservice.repository;

import java.util.List;
import kr.jay.paymentservice.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PaymentMethodRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByUserId(Long userId);
}
