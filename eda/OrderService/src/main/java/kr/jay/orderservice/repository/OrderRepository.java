package kr.jay.orderservice.repository;

import java.util.List;
import kr.jay.orderservice.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByUserId(Long userId);
}
