package kr.jay.paymentservice.repository;

import kr.jay.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PaymentRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
