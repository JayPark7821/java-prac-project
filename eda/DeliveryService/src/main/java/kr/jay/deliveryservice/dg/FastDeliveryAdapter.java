package kr.jay.deliveryservice.dg;

import org.springframework.stereotype.Component;

/**
 * FastDeliveryAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Component
public class FastDeliveryAdapter implements DeliveryAdapter{

    @Override
    public Long processDelivery(String productName, Long productCount, String address) {
        return 778811L;
    }
}
