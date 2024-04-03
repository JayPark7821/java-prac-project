package kr.jay.deliveryservice.dg;

/**
 * DeliveryAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
public interface DeliveryAdapter {

    Long processDelivery(String productName, Long productCount, String address);

}
