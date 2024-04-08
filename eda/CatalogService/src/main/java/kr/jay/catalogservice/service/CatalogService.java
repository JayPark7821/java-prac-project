package kr.jay.catalogservice.service;

import edaprac.protobuf.EdaMessage.ProductTags;
import java.util.List;
import java.util.Optional;
import kr.jay.catalogservice.cassandra.entity.Product;
import kr.jay.catalogservice.cassandra.repository.ProductRepository;
import kr.jay.catalogservice.mysql.entity.SellerProduct;
import kr.jay.catalogservice.mysql.repository.SellerProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * CatalogService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Service
@RequiredArgsConstructor
public class CatalogService {

    private final SellerProductRepository sellerProductRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;


    public Product registerProduct(
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
    ) {
        SellerProduct savedSellerProduct = sellerProductRepository.save(new SellerProduct(sellerId));

        //        searchClient.addTagCache(new ProductTagsDto(savedSellerProduct.getId(),tags));
        byte[] message = ProductTags.newBuilder()
            .setProductId(savedSellerProduct.getId())
            .addAllTags(tags)
            .build()
            .toByteArray();

        kafkaTemplate.send("product_tags_added", message);

        return productRepository.save(
            new Product(
                savedSellerProduct.getId(),
                sellerId,
                name,
                description,
                price,
                stockCount,
                tags
            )
        );
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
//        searchClient.removeCache(new ProductTagsDto(product.getId(),product.getTags()));

        byte[] message = ProductTags.newBuilder()
            .setProductId(product.getId())
            .addAllTags(product.getTags())
            .build()
            .toByteArray();

        kafkaTemplate.send("product_tags_removed", message);

        productRepository.delete(product);
        sellerProductRepository.deleteById(productId);
    }

    public List<Product> getProductsBySellerId(Long sellerId) {
        List<SellerProduct> sellerProducts = sellerProductRepository.findBySellerId(sellerId);
        return sellerProducts
            .stream()
            .map(sellerProduct -> productRepository.findById(sellerProduct.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    public Product decreaseStockCount(Long productId, Long count) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setStockCount(product.getStockCount() - count);
        return productRepository.save(product);
    }
}
