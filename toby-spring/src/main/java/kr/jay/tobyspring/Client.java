package kr.jay.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import kr.jay.tobyspring.payment.Payment;
import kr.jay.tobyspring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Client
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
