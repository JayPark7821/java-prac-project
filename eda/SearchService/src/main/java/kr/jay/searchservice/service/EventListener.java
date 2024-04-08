package kr.jay.searchservice.service;

import com.google.protobuf.InvalidProtocolBufferException;
import edaprac.protobuf.EdaMessage.ProductTags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * EventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/8/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private final SearchService searchService;

    @KafkaListener(topics = "product-tags-added")
    public void consumeTagAdded(byte[] message) throws InvalidProtocolBufferException {
        ProductTags productTags = ProductTags.parseFrom(message);
        log.info("product tags added: {}", productTags);

        searchService.addTagCache(productTags.getProductId(), productTags.getTagsList());
    }
    @KafkaListener(topics = "product-tags-removed")
    public void consumeTagRemoved(byte[] message) throws InvalidProtocolBufferException {
        ProductTags productTags = ProductTags.parseFrom(message);
        log.info("product tags removed: {}", productTags);

        searchService.removeTagCache(productTags.getProductId(), productTags.getTagsList());
    }
}
