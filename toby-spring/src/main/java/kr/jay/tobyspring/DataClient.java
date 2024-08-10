package kr.jay.tobyspring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import kr.jay.tobyspring.order.Order;
import kr.jay.tobyspring.payment.PaymentService;
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
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order order = new Order("100",  BigDecimal.TEN);
        System.out.println(order);
        em.persist(order);
        System.out.println(order);

        em.getTransaction().commit();
        em.clear();
    }

}
