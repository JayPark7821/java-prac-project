package kr.jay.tobyspring.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * OrderServiceTxProxy
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/16/24
 */
public class OrderServiceTxProxy implements OrderService{

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return target.createOrder(no, total);
    }

    @Override
    public List<Order> createOrder(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status->
            target.createOrder(reqs)
        );
    }
}
