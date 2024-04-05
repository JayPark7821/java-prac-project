package kr.jay.orderservice.feign;

import java.util.Map;
import kr.jay.orderservice.dto.DecreaseStockCountRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CatalogClient
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/5/24
 */
@FeignClient(name = "catalogClient", url="http://catalog-service:8080")
public interface CatalogClient {

    @GetMapping("/catalog/products/{productId}")
    Map<String, Object> getProduct(@PathVariable("productId") Long productId);

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    void decreaseStockCount(@PathVariable("productId") Long productId, @RequestBody DecreaseStockCountRequest request);

}
