package kr.jay.paymentservice.pg;

/**
 * CreditCardPaymentAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/1/24
 */
public interface CreditCardPaymentAdapter {

    Long processCreditCardPayment(Long amountKRW, String creditCardNumber);

}
