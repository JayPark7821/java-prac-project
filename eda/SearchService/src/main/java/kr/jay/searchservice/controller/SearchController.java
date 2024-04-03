package kr.jay.searchservice.controller;

import java.util.List;
import kr.jay.searchservice.dto.ProductTagsDto;
import kr.jay.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SearchController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 4/3/24
 */
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/search/addTagCache")
    void addTagCache(@RequestBody ProductTagsDto request) {
        searchService.addTagCache(request.productId(), request.tags());
    }

    @PostMapping("/search/removeTagCache")
    void removeTagCache(@RequestBody ProductTagsDto request) {
        searchService.removeTagCache(request.productId(), request.tags());
    }

    @GetMapping("/search/tags/{tag}/productIds")
    void getTagProductIds(@PathVariable("tag") String tag ) {
        searchService.getProductIdByTag(tag);
    }
}
