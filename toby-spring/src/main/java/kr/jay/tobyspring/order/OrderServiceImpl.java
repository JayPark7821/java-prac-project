package kr.jay.tobyspring.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/12/24
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(
        OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        orderRepository.save(order);
        return order;

    }

    @Override
    public List<Order> createOrder(List<OrderReq> reqs) {
        return reqs.stream().map(req -> createOrder(req.no(), req.total())).toList();
    }
}
