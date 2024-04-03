package kr.jay.deliveryservice.repository;

import java.util.List;
import kr.jay.deliveryservice.entity.Delivery;
import kr.jay.deliveryservice.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserAddressRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findByUserId(Long userId);
}
