package kr.jay.tobyspring;

import javax.sql.DataSource;
import kr.jay.tobyspring.data.JdbcOrderRepository;
import kr.jay.tobyspring.data.JpaOrderRepository;
import kr.jay.tobyspring.order.OrderRepository;
import kr.jay.tobyspring.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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
    public OrderService orderService(
        PlatformTransactionManager transactionManager,
        OrderRepository orderRepository
    ) {
        return new OrderService(orderRepository, transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
