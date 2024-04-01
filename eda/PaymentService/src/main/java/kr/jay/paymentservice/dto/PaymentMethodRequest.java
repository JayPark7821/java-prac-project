package kr.jay.paymentservice.dto;

import kr.jay.paymentservice.enums.PaymentMethodType;

/**
 * PaymentMethodRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/1/24
 */
public class PaymentMethodRequest {
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private String creditCardNumber;

    public Long getUserId() {
        return userId;
    }

    public PaymentMethodType getPaymentMethodType() {
        return paymentMethodType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
}
