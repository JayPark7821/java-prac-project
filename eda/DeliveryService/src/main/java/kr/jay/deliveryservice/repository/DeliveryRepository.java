package kr.jay.deliveryservice.repository;

import java.util.List;
import kr.jay.deliveryservice.entity.Delivery;
import kr.jay.deliveryservice.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DeliveryRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByOrderId(Long orderId);

    List<Delivery> findAllByStatus(DeliveryStatus deliveryStatus);
}
