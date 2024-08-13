package kr.jay.tobyspring;

import kr.jay.tobyspring.data.JpaOrderRepository;
import kr.jay.tobyspring.order.OrderRepository;
import kr.jay.tobyspring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 * OrderConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/12/24
 */
@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(JpaTransactionManager jpaTransactionManager){
        return new OrderService(orderRepository(), jpaTransactionManager);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository();
    }
}
