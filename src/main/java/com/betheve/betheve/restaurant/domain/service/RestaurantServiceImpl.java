package com.betheve.betheve.restaurant.domain.service;

import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.entity.dto.RegisterRestaurantDto;
import com.betheve.betheve.restaurant.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(RegisterRestaurantDto restaurantDto) {
        //Todo Dto 클래스
        Restaurant newRestaurant = new Restaurant();

        restaurantRepository.save(newRestaurant);

        return newRestaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurant(long restaurantId) {

        return restaurantRepository.findById(restaurantId).orElseThrow();
    }

    @Override
    public void deleteRestaurant(long restaurantId) {

        restaurantRepository.deleteById(restaurantId);
    }
}
