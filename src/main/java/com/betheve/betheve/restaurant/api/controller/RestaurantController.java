package com.betheve.betheve.restaurant.api.controller;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.entity.dto.RegisterRestaurantDto;
import com.betheve.betheve.restaurant.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "전체 식당 조회", description = "등록된 모든 식당을 조회합니다.")
    @GetMapping
    public List<Restaurant> getAllRestaurants() {

        return restaurantService.getAllRestaurants();

    }

    @Operation(summary = "식당 조회", description = "식당을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRestaurant(@PathVariable Long id) {

        return new ResponseEntity<>(restaurantService.getRestaurant(id), HttpStatus.OK) ;

    }

    @Operation(summary = "식당 신규등록", description = "새로운 식당을 등록합니다.")
    @PostMapping
    public Restaurant resisterRestaurant(@RequestBody RegisterRestaurantDto restaurantDto) {

        return restaurantService.create(restaurantDto);

    }

    @Operation(summary = "식당 삭제", description = "식당 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable long id) {

        restaurantService.deleteRestaurant(id);

    }



}
