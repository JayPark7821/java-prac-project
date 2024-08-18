package kr.jay.tobyspring;

import javax.sql.DataSource;
import kr.jay.tobyspring.data.JdbcOrderRepository;
import kr.jay.tobyspring.order.OrderRepository;
import kr.jay.tobyspring.order.OrderService;
import kr.jay.tobyspring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * OrderConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/12/24
 */
@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

    @Bean
    public OrderService orderService(
        OrderRepository orderRepository
    ) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
