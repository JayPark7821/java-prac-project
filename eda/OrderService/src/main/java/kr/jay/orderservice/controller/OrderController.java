package kr.jay.orderservice.controller;

import java.util.List;
import kr.jay.orderservice.dto.FinishOrderRequest;
import kr.jay.orderservice.dto.ProductOrderDetailResponse;
import kr.jay.orderservice.dto.StartOrderRequest;
import kr.jay.orderservice.dto.StartOrderResponse;
import kr.jay.orderservice.entity.ProductOrder;
import kr.jay.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/start-order")
    StartOrderResponse startOrder(@RequestBody StartOrderRequest request) {
        return orderService.startOrder(request.userId(), request.productId(), request.count());
    }

    @PostMapping("/order/finish-order")
    ProductOrder startOrder(@RequestBody FinishOrderRequest request) {
        return orderService.finishOrder(request.orderId(), request.paymentMethodId(), request.addressId());
    }

    @GetMapping("/order/users/{userId}/orders")
    List<ProductOrder> getUserOrders(@PathVariable("userId") Long userId) {
        return orderService.getUserOrders(userId);
    }

    @GetMapping("/order/ordrs/{orderId}")
    ProductOrderDetailResponse getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderDetail(orderId);
    }



}
