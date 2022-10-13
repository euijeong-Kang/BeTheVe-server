package com.betheve.betheve.restaurant.domain.service;

import com.betheve.betheve.restaurant.domain.entity.ReTable;
import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.restaurant.domain.entity.dto.RegisterRestaurantDto;
import com.betheve.betheve.restaurant.domain.repository.ReTableRepository;
import com.betheve.betheve.restaurant.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final ReTableRepository repository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ReTableRepository repository) {
        this.restaurantRepository = restaurantRepository;
        this.repository = repository;
    }

    @Override
    public Restaurant create(RegisterRestaurantDto restaurantDto) {
        Restaurant newRestaurant = new Restaurant(restaurantDto.getRestaurantName(), restaurantDto.getPhoneNum(), restaurantDto.isHasParking());

        restaurantRepository.save(newRestaurant);

        return newRestaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurant(long restaurantId) {
        Restaurant r = restaurantRepository.findById(restaurantId).get();
        return r;
    }

    @Override
    public void deleteRestaurant(long restaurantId) {

        restaurantRepository.deleteById(restaurantId);
    }

    public void setData() {
        List<ReTable> list = repository.findAll();
        list.forEach(reTable -> {restaurantRepository.save(new Restaurant(reTable.getRestaurantName(), reTable.getPhoneNum(), reTable.isHasParking(), reTable.getLocation()));});
    }
}
