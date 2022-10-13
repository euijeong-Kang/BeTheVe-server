package com.betheve.betheve.restaurant.domain.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


@Schema
@Getter
public class RegisterRestaurantDto {

    private String restaurantName;

    private String phoneNum;

    private boolean hasParking;

}
