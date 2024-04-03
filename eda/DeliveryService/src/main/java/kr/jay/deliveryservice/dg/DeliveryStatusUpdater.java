package kr.jay.deliveryservice.dg;

import kr.jay.deliveryservice.enums.DeliveryStatus;
import kr.jay.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Scheduled(fixedRate = 10000)
    public void deliveryStatusUpdate() {
        log.info(" ============== Delivery Status Update Start ============== ");

        deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.COMPLETED);
                deliveryRepository.save(delivery);
            });

        deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.IN_DELIVERY);
                deliveryRepository.save(delivery);
            });
    }
}
