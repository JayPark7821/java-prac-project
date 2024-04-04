package kr.jay.catalogservice.controller;

import java.util.List;
import kr.jay.catalogservice.cassandra.entity.Product;
import kr.jay.catalogservice.dto.DecreaseStockCountDto;
import kr.jay.catalogservice.dto.RegisterProductDto;
import kr.jay.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CatalogController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/catalog/products")
    Product registerProduct(@RequestBody RegisterProductDto request) {
        return catalogService.registerProduct(
            request.sellerId(),
            request.name(),
            request.description(),
            request.price(),
            request.stockCount(),
            request.tags()
        );
    }

    @DeleteMapping("/catalog/products/{productId}")
    void deleteProduct(@PathVariable("productId") Long productId) {
        catalogService.deleteProduct(productId);
    }

    @GetMapping("/catalog/products/{productId}")
    Product getProductById(@PathVariable("productId") Long productId) {
        return catalogService.getProductById(productId);
    }

    @GetMapping("/catalog/sellers/{sellerId}/products")
    List<Product> getProductsBySellerId(@PathVariable("sellerId") Long sellerId) {
        return catalogService.getProductsBySellerId(sellerId);
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    Product decreaseStockCount(
        @PathVariable("productId") Long productId,
        @RequestBody DecreaseStockCountDto request
    ) {
        return catalogService.decreaseStockCount(productId, request.decreaseCount());
    }
}
