package kr.jay.searchservice.controller;

import kr.jay.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/search/tags/{tag}/productIds")
    void getTagProductIds(@PathVariable("tag") String tag ) {
        searchService.getProductIdByTag(tag);
    }
}
