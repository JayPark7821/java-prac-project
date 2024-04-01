package kr.jay.paymentservice.service;

import kr.jay.paymentservice.entity.Payment;
import kr.jay.paymentservice.entity.PaymentMethod;
import kr.jay.paymentservice.enums.PaymentMethodType;
import kr.jay.paymentservice.enums.PaymentStatus;
import kr.jay.paymentservice.pg.CreditCardPaymentAdapter;
import kr.jay.paymentservice.repository.PaymentMethodRepository;
import kr.jay.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

/**
 * PaymentService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@Service
public class PaymentService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    public PaymentService(
        PaymentMethodRepository paymentMethodRepository,
        PaymentRepository paymentRepository,
        CreditCardPaymentAdapter creditCardPaymentAdapter
    ) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentRepository = paymentRepository;
        this.creditCardPaymentAdapter = creditCardPaymentAdapter;
    }

    public PaymentMethod registerPayment(
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber
    ) {
        return paymentMethodRepository.save(
            new PaymentMethod(userId, paymentMethodType, creditCardNumber)
        );
    }

    public Payment processPayment(
        Long userId,
        Long orderId,
        Long amountKRW,
        Long paymentMethodId
    ) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow(() -> new IllegalArgumentException("PaymentMethod not found"));

        if (paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD) {
            throw new IllegalArgumentException("Unsupported payment method type");
        }

        Long refCode = creditCardPaymentAdapter.processCreditCardPayment(
            amountKRW,
            paymentMethod.getCreditCardNumber()
        );

        return paymentRepository.save(
            new Payment(
                userId,
                orderId,
                amountKRW,
                PaymentMethodType.CREDIT_CARD,
                paymentMethod.getCreditCardNumber(),
                PaymentStatus.COMPLETED,
                refCode
            )
        );
    }

    public PaymentMethod getPaymentMethodBy(Long userId) {
        return paymentMethodRepository.findByUserId(userId).stream().findFirst()
            .orElseThrow(() -> new IllegalArgumentException("PaymentMethod not found"));
    }

    public Payment getPaymentBy(Long paymentId) {
        return paymentRepository.findById(paymentId)
            .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
