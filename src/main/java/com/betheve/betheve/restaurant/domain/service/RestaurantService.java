package com.betheve.betheve.restaurant.domain.service;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.entity.dto.RegisterRestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {

    Restaurant create(RegisterRestaurantDto restaurantDto);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(long restaurantId);

    void deleteRestaurant(long restaurantId);

}
