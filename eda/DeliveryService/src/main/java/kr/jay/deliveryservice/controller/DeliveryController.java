package kr.jay.deliveryservice.controller;

import kr.jay.deliveryservice.dto.ProcessDeliveryDto;
import kr.jay.deliveryservice.dto.RegisterAddressDto;
import kr.jay.deliveryservice.entity.Delivery;
import kr.jay.deliveryservice.entity.UserAddress;
import kr.jay.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeliveryController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery/addresses")
    UserAddress registerAddress(@RequestBody RegisterAddressDto request) {
        return deliveryService.addUserAddress(
            request.userId(),
            request.address(),
            request.alias()
        );
    }

    @PostMapping("/delivery/process-delivery")
    Delivery processDelivery(@RequestBody ProcessDeliveryDto request) {
        return deliveryService.processDelivery(
            request.orderId(),
            request.productName(),
            request.productCount(),
            request.address()
        );
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    Delivery getDelivery(@PathVariable("deliveryId") Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/delivery/deliveries/{addressId}")
    UserAddress getAddress(@PathVariable("addressId") Long addressId) {
        return deliveryService.getAddress(addressId);
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    UserAddress getUserAddress(@PathVariable("userId") Long userId) {
        return deliveryService.getUserAddress(userId);
    }
}
