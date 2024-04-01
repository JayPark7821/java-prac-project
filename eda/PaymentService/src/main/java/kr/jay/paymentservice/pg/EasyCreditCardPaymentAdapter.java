package kr.jay.paymentservice.pg;

import org.springframework.stereotype.Service;

/**
 * EasyCreditCardPaymentAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/1/24
 */
@Service
public class EasyCreditCardPaymentAdapter implements CreditCardPaymentAdapter{

    @Override
    public Long processCreditCardPayment(Long amountKRW, String creditCardNumber) {
        return Math.round(Math.random() * 1000000L);
    }
}
