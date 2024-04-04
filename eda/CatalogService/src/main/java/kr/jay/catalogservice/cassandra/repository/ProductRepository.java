package kr.jay.catalogservice.cassandra.repository;

import kr.jay.catalogservice.cassandra.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public interface ProductRepository extends CassandraRepository<Product, Long>{

}
