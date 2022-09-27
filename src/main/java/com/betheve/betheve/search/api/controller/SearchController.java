package com.betheve.betheve.search.api.controller;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.search.domain.service.SearchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchServiceImpl searchService;

    @Autowired
    public SearchController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    @Operation
    @GetMapping("/restaurant/{searchKey}")
    public List<Restaurant> searchRestaurant(@PathVariable String searchKey) {
        return searchService.searchRestaurant(searchKey);
    }
}
