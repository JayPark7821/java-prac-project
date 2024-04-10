package kr.jay.deliveryservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import edaprac.protobuf.EdaMessage.DeliveryRequestV1;
import edaprac.protobuf.EdaMessage.DeliveryStatusUpdateV1;
import kr.jay.deliveryservice.entity.Delivery;
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

    private final DeliveryService deliveryService;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @KafkaListener(topics = "delivery_request")
    public void consumePaymentRequest(byte[] message) throws InvalidProtocolBufferException {
        DeliveryRequestV1 deliveryRequest = DeliveryRequestV1.parseFrom(message);
        log.info("delivery request: {}", deliveryRequest);

        Delivery delivery = deliveryService.processDelivery(
            deliveryRequest.getOrderId(),
            deliveryRequest.getProductName(),
            deliveryRequest.getProductCount(),
            deliveryRequest.getAddress()
        );

        DeliveryStatusUpdateV1 deliveryStatusUpdate = DeliveryStatusUpdateV1.newBuilder()
            .setDeliveryId(delivery.getId())
            .setOrderId(delivery.getOrderId())
            .setDeliveryStatus(delivery.getStatus().name())
            .build();

        kafkaTemplate.send("delivery_status_update", deliveryStatusUpdate.toByteArray());
    }
}
