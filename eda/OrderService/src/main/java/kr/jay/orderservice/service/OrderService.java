package kr.jay.orderservice.service;

import java.util.Map;
import kr.jay.orderservice.dto.DecreaseStockCountRequest;
import kr.jay.orderservice.dto.ProcessDeliveryRequest;
import kr.jay.orderservice.dto.ProcessPaymentRequest;
import kr.jay.orderservice.dto.ProductOrderDetailResponse;
import kr.jay.orderservice.dto.StartOrderResponse;
import kr.jay.orderservice.entity.ProductOrder;
import kr.jay.orderservice.enums.OrderStatus;
import kr.jay.orderservice.feign.CatalogClient;
import kr.jay.orderservice.feign.DeliveryClient;
import kr.jay.orderservice.feign.PaymentClient;
import kr.jay.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final PaymentClient paymentClient;

    private final DeliveryClient deliveryClient;

    private final CatalogClient catalogClient;

    public StartOrderResponse startOrder(Long userId, Long productId, Long count) {
        // 1. 상품 정보 조회
        Map<String, Object> product = catalogClient.getProduct(productId);

        // 2. 결제 수단 정보 조회
        Map<String, Object> paymentMethod = paymentClient.getPaymentMethod(userId);

        // 3. 배송지 정보 조회
        Map<String, Object> address = deliveryClient.getUserAddress(userId);

        // 4. 주문 정보 생성
        var order = new ProductOrder(
                userId,
                productId,
                count,
                OrderStatus.INITIATED,
                null,
                null
        );
        orderRepository.save(order);

        return new StartOrderResponse(order.getId(),paymentMethod, address);
    }

    public ProductOrder finishOrder(Long orderId, Long paymentMethodId, Long addressId) {
        ProductOrder order = orderRepository.findById(orderId).orElseThrow();

        // 1. 상품 정보 조회
        Map<String, Object> product = catalogClient.getProduct(order.getProductId());

        // 2. 결제

        Map<String, Object> payment = paymentClient.processPayment(
            new ProcessPaymentRequest(order.getId(), order.getUserId(),
                Long.parseLong(product.get("price").toString()) * order.getCount(), paymentMethodId)
        );

        // 3. 배송 요청
        Map<String, Object> address = deliveryClient.getAddress(addressId);

        Map<String, Object> delivery = deliveryClient.processDelivery(
            new ProcessDeliveryRequest(order.getId(), product.get("name").toString(), order.getCount(),
                address.get("address").toString())
        );

        // 4. 상품 재고 감소
        catalogClient.decreaseStockCount(order.getProductId(), new DecreaseStockCountRequest(order.getCount()));

        // 5. 주문 정보 업데이트
        order.finishOrder(
            Long.parseLong(payment.get("id").toString()),
            Long.parseLong(delivery.get("id").toString())
        );

        return orderRepository.save(order);
    }

    public List<ProductOrder> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public ProductOrderDetailResponse getOrderDetail(Long orderId) {
        ProductOrder order = orderRepository.findById(orderId).orElseThrow();

        Map<String, Object> payment = paymentClient.getPayment(order.getPaymentId());
        Map<String, Object> delivery = deliveryClient.getDelivery(order.getDeliveryId());

        return new ProductOrderDetailResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getPaymentId(),
                order.getDeliveryId(),
                order.getOrderStatus(),
                payment.get("paymentStatus").toString(),
                delivery.get("status").toString()
        );
    }
}
