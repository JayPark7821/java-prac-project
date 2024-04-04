package kr.jay.catalogservice.mysql.repository;

import java.util.List;
import kr.jay.catalogservice.mysql.entity.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SellerProductRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public interface SellerProductRepository extends JpaRepository<SellerProduct, Long> {

    List<SellerProduct> findBySellerId(Long sellerId);

}
