package kr.jay.tobyspring.order;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import kr.jay.tobyspring.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * OrderServiceSpringTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/13/24
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() throws Exception{
        var order = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() throws Exception{
        //given
        List<OrderReq> orderReqs = List.of(
            new OrderReq("0200", BigDecimal.ONE),
            new OrderReq("0201", BigDecimal.TWO)
        );

        //when
        List<Order> orders = orderService.createOrder(orderReqs);
        //then
        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() throws Exception{
        //given
        List<OrderReq> orderReqs = List.of(
            new OrderReq("0300", BigDecimal.ONE),
            new OrderReq("0300", BigDecimal.TWO)
        );

        //when
        assertThatThrownBy(()->orderService.createOrder(orderReqs))
            .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        Long count = client.sql("select count(*) from orders where no = '0300'")
            .query(Long.class)
            .single();

        assertThat(count).isEqualTo(0);

    }

}
