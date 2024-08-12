package kr.jay.tobyspring;

import java.math.BigDecimal;
import kr.jay.tobyspring.order.Order;
import kr.jay.tobyspring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * OrderClient
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/10/24
 */
public class OrderClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        System.out.println("order = " + order);
    }
}
