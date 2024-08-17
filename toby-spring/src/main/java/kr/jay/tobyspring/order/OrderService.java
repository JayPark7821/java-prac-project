package kr.jay.tobyspring.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/16/24
 */
public interface OrderService {

    Order createOrder(String no, BigDecimal total);

    List<Order> createOrder(List<OrderReq> reqs);
}
