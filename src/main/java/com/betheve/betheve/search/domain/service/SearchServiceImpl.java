package com.betheve.betheve.search.domain.service;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public SearchServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> searchRestaurant(String searchKeys) {
        List<Restaurant> list = restaurantRepository.findAllByRestaurantNameContains(searchKeys);
        return list;
    }
}
