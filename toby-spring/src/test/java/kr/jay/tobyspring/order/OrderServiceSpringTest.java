package kr.jay.tobyspring.order;

import java.math.BigDecimal;
import kr.jay.tobyspring.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    void createOrder() throws Exception{
        var order = orderService.createOrder("100", BigDecimal.TEN);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }

}
