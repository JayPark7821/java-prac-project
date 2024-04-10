package kr.jay.deliveryservice.dg;

import edaprac.protobuf.EdaMessage.DeliveryStatusUpdateV1;
import kr.jay.deliveryservice.entity.Delivery;
import kr.jay.deliveryservice.enums.DeliveryStatus;
import kr.jay.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * DeliveryStatusUpdater
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryStatusUpdater {

    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Scheduled(fixedRate = 10000)
    public void deliveryStatusUpdate() {
        log.info(" ============== Delivery Status Update Start ============== ");

        deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.COMPLETED);
                deliveryRepository.save(delivery);
                publishStatusChange(delivery);
            });

        deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.IN_DELIVERY);
                deliveryRepository.save(delivery);
                publishStatusChange(delivery);
            });
    }

    private void publishStatusChange(Delivery delivery){

        DeliveryStatusUpdateV1 deliveryStatusUpdate = DeliveryStatusUpdateV1.newBuilder()
            .setDeliveryId(delivery.getId())
            .setOrderId(delivery.getOrderId())
            .setDeliveryStatus(delivery.getStatus().name())
            .build();

        kafkaTemplate.send("delivery_status_update", deliveryStatusUpdate.toByteArray());
    }
}
