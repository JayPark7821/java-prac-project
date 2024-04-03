package kr.jay.searchservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * SearchService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final StringRedisTemplate stringRedisTemplate;

    public void addTagCache(Long productId, List<String> tags) {
        tags.forEach(tag ->
            stringRedisTemplate.opsForSet()
                .add(tag, productId.toString())
        );
    }

    public void removeTagCache(Long productId, List<String> tags) {
        tags.forEach(tag ->
            stringRedisTemplate.opsForSet()
                .remove(tag, productId.toString())
        );
    }

    public List<Long> getProductIdByTag(String tag) {
        Set<String> productIds = stringRedisTemplate.opsForSet()
            .members(tag);
        return productIds != null ?
            productIds
            .stream()
            .map(Long::parseLong)
            .toList() :
            Collections.emptyList();

    }
}
