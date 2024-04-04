package kr.jay.catalogservice.feign;

import kr.jay.catalogservice.dto.ProductTagsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * SearchClient
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@FeignClient(name ="searchClient", url = "http://search-service:8080")
public interface SearchClient {

    @PostMapping(value ="/search/addTagCache")
    void addTagCache(@RequestBody ProductTagsDto request);

    @DeleteMapping(value ="/search/addTagCache")
    void removeCache(@RequestBody ProductTagsDto request);
}
