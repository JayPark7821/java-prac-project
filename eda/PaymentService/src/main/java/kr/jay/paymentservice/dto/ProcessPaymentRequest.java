package kr.jay.paymentservice.dto;

/**
 * ProcessPaymentRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/1/24
 */
public class ProcessPaymentRequest {
    private Long userId;
    private Long orderId;
    private Long amountKRW;
    private Long paymentMethodId;

    public Long getUserId() {
        return userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getAmountKRW() {
        return amountKRW;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }
}
