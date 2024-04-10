package kr.jay.paymentservice.controller;

import kr.jay.paymentservice.dto.PaymentMethodRequest;
import kr.jay.paymentservice.entity.Payment;
import kr.jay.paymentservice.entity.PaymentMethod;
import kr.jay.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PaymentController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/methods")
    PaymentMethod registerPaymentMethod(@RequestBody PaymentMethodRequest request){
        return paymentService.registerPayment(
            request.getUserId(),
            request.getPaymentMethodType(),
            request.getCreditCardNumber()
        );
    }

    @GetMapping("/payment/users/{userId}/frist-methods")
    PaymentMethod getPaymentMethod(@PathVariable("userId") Long userId){
        return paymentService.getPaymentMethodBy(userId);
    }

    @GetMapping("/payment/payments/{paymentsId}")
    Payment getPayment(@PathVariable("paymentsId") Long paymentsId){
        return paymentService.getPaymentBy(paymentsId);
    }
}
