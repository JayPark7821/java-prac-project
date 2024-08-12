package kr.jay.tobyspring;

import java.math.BigDecimal;
import kr.jay.tobyspring.data.OrderRepository;
import kr.jay.tobyspring.order.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try{
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.TEN);
                repository.save(order2);
                return null;
            });
        }catch (DataIntegrityViolationException e){
            System.out.println("주문번호 중복 복구 작업");
        }


    }
}
