package kr.jay.tobyspring;

import java.math.BigDecimal;
import kr.jay.tobyspring.data.OrderRepository;
import kr.jay.tobyspring.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DataClient
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/10/24
 */
public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);

        Order order2 = new Order("100", BigDecimal.TEN);
        repository.save(order2);
    }

}
