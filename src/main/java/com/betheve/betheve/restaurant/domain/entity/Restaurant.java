package com.betheve.betheve.restaurant.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "Restaurant")
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId;

    private String restaurantName;

    private String PhoneNum;

    @Embedded
    private BusinessHour openingHours;

    @Embedded
    private BusinessHour breakTime;

    private boolean hasParking;

    private LocalDateTime resisterDate;

    private LocalDateTime lastUpdatedDate;


    public Restaurant() {}

}
