package kr.jay.tobyspring.order;

import java.math.BigDecimal;
import java.util.List;
import kr.jay.tobyspring.data.JpaOrderRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * OrderService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/12/24
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(
        OrderRepository orderRepository,
        PlatformTransactionManager transactionManager
    ) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

            orderRepository.save(order);
            return order;

    }

    public List<Order> createOrder(List<OrderReq> reqs){
        return new TransactionTemplate(transactionManager).execute(status->
            reqs.stream().map(req -> createOrder(req.no(), req.total())).toList()
        );
    }
}
