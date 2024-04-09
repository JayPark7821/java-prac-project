package kr.jay.paymentservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import edaprac.protobuf.EdaMessage.PaymentRequestV1;
import edaprac.protobuf.EdaMessage.PaymentResultV1;
import edaprac.protobuf.EdaMessage.ProductTags;
import kr.jay.paymentservice.entity.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * EventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/8/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private final PaymentService paymentService;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @KafkaListener(topics = "payment_request")
    public void consumePaymentRequest(byte[] message) throws InvalidProtocolBufferException {
        PaymentRequestV1 paymentRequest = PaymentRequestV1.parseFrom(message);
        log.info("payment request: {}", paymentRequest);

        Payment payment = paymentService.processPayment(
            paymentRequest.getUserId(),
            paymentRequest.getOrderId(),
            paymentRequest.getAmountKRW(),
            paymentRequest.getPaymentMethodId()
        );

        PaymentResultV1 paymentResult = PaymentResultV1.newBuilder()
            .setOrderId(payment.getOrderId())
            .setPaymentId(payment.getId())
            .setPaymentStatus(payment.getPaymentStatus().name())
            .build();

        kafkaTemplate.send("payment_result", paymentResult.toByteArray());
    }
}
