package kr.jay.deliveryservice.service;

import kr.jay.deliveryservice.dg.DeliveryAdapter;
import kr.jay.deliveryservice.entity.Delivery;
import kr.jay.deliveryservice.entity.UserAddress;
import kr.jay.deliveryservice.enums.DeliveryStatus;
import kr.jay.deliveryservice.repository.DeliveryRepository;
import kr.jay.deliveryservice.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * DeliveryService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final UserAddressRepository userAddressRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAdapter deliveryAdapter;

    public UserAddress addUserAddress(Long userId, String address, String alias) {
        return userAddressRepository.save(new UserAddress(userId, address, alias));
    }

    public Delivery processDelivery(
        Long orderId,
        String productName,
        Long productCount,
        String address
    ) {
        Long refCode = deliveryAdapter.processDelivery(productName, productCount, address);

        return deliveryRepository.save(
            new Delivery(
                orderId,
                productName,
                productCount,
                address,
                DeliveryStatus.REQUESTED,
                refCode
            ));
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow();
    }

    public UserAddress getAddress(Long addressId) {
        return userAddressRepository.findById(addressId).orElseThrow();
    }

    public UserAddress getUserAddress(Long userId) {
        return userAddressRepository.findByUserId(userId)
            .stream()
            .findFirst()
            .orElseThrow();
    }
}
