package com.betheve.betheve.restaurant.api.controller;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.entity.dto.RegisterRestaurantDto;
import com.betheve.betheve.restaurant.domain.service.RestaurantServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "전체 식당 조회", description = "등록된 모든 식당을 조회합니다.")
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {

        return restaurantService.getAllRestaurants();

    }

    @Operation(summary = "식당 조회", description = "식당을 조회합니다.")
    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {

        return restaurantService.getRestaurant(id);

    }

    @Operation(summary = "식당 신규등록", description = "새로운 식당을 등록합니다.")
    @PostMapping
    public Restaurant resisterRestaurant(@RequestParam RegisterRestaurantDto restaurantDto) {

        return restaurantService.create(restaurantDto);

    }

    @Operation(summary = "식당 삭제", description = "식당 정보를 삭제합니다.")
    @DeleteMapping("/restaurant/{id}")
    public void deleteRestaurant(@PathVariable long id) {

        restaurantService.deleteRestaurant(id);

    }



}
