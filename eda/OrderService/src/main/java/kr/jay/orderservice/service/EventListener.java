package kr.jay.orderservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import edaprac.protobuf.EdaMessage.DeliveryRequestV1;
import edaprac.protobuf.EdaMessage.DeliveryStatusUpdateV1;
import edaprac.protobuf.EdaMessage.PaymentResultV1;
import java.util.Map;
import kr.jay.orderservice.dto.DecreaseStockCountRequest;
import kr.jay.orderservice.entity.ProductOrder;
import kr.jay.orderservice.feign.CatalogClient;
import kr.jay.orderservice.repository.OrderRepository;
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

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final CatalogClient catalogClient;

    @KafkaListener(topics = "payment_result")
    public void consumePaymentResult(byte[] message) throws InvalidProtocolBufferException {
        PaymentResultV1 paymentResult = PaymentResultV1.parseFrom(message);
        log.info("payment result: {}", paymentResult);

        // 결제정보 update
        ProductOrder order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
        order.finishOrder(
            paymentResult.getPaymentId(),
            paymentResult.getOrderId()
        );

        Map<String, Object> product = catalogClient.getProduct(order.getProductId());

        DeliveryRequestV1 deliveryRequest = DeliveryRequestV1.newBuilder()
            .setOrderId(order.getId())
            .setProductName(product.get("name").toString())
            .setProductCount(order.getCount())
            .setAddress(order.getDeliveryAddress())
            .build();

        kafkaTemplate.send("delivery_request", deliveryRequest.toByteArray());
    }

    @KafkaListener(topics = "delivery_status_update")
    public void consumeDeliveryStatusUpdate(byte[] message) throws InvalidProtocolBufferException {
        DeliveryStatusUpdateV1 deliveryStatusUpdate  = DeliveryStatusUpdateV1.parseFrom(message);
        log.info("delivery status update: {}", deliveryStatusUpdate);

        if(deliveryStatusUpdate.getDeliveryStatus().equals("REQUESTED")){

            ProductOrder order = orderRepository.findById(deliveryStatusUpdate.getOrderId()).orElseThrow();
            catalogClient.decreaseStockCount(
                order.getProductId(),
                new DecreaseStockCountRequest(order.getCount())
            );
        }
    }
}
