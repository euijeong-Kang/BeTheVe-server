package com.betheve.betheve.advertise.recommend.domain.entity;

import com.betheve.betheve.advertise.recommend.domain.entity.dto.RestaurantIdDto;
import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recommendId;

    private String recommendTitle;

    private String subTitle;

    @ElementCollection
    @CollectionTable(
            name = "recommend_group",
            joinColumns = @JoinColumn(name = "recommend_id")
    )
    @OrderColumn(name = "restaurant_id")
    private List<Restaurant> recommendedRestaurantGroup;

}
