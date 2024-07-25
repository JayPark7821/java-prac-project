package kr.jay.tobyspring;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Client
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/24/24
 */
public class Client {
    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }

}
