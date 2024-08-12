package kr.jay.tobyspring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.jay.tobyspring.order.Order;

/**
 * OrderRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/10/24
 */
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }
}
