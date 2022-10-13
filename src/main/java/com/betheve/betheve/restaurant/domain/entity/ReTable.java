package com.betheve.betheve.restaurant.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ReTable {
    @Id
    private String restaurantName;

    private String phoneNum;

    private boolean hasParking;

//    private Certification certification;
//
//    private VeganLevel veganLevel;
//
//    private RestaurantType restaurantType;

    private String location;
}
