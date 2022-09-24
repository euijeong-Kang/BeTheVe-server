package com.betheve.betheve.restaurant.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "restaurant")
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
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

    private VeganLevel veganLevel;

    private RestaurantType restaurantType;

    private boolean hasParking;

    private LocalDateTime resisterDate;

    private LocalDateTime lastUpdatedDate;

    private Certification certification;

    @ElementCollection
    @CollectionTable (
            name = "menu_list",
            joinColumns = @JoinColumn(name = "restaurant_id")
    )
    @OrderColumn(name = "menu_id")
    List<Menu> menuList;



}
