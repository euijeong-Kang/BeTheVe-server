package com.betheve.betheve.advertise.recommend.domain.entity;

import com.betheve.betheve.advertise.recommend.domain.entity.dto.RestaurantIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recommendId;

    private String recommendTitle;

    private String subTitle;

    private List<RestaurantIdDto> recommendedRestaurantGroup;



}
