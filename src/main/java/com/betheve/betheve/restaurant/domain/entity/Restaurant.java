package com.betheve.betheve.restaurant.domain.entity;

import com.betheve.betheve.gallery.domain.entity.GalleryImage;
import com.betheve.betheve.review.domain.entity.Review;
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

    private String phoneNum;

//    @Embedded
//    private BusinessHour openingHours;
//
//    @Embedded
//    private BusinessHour breakTime;

//    private VeganLevel veganLevel;
//
//    private RestaurantType restaurantType;

    private boolean hasParking;

    private LocalDateTime resisterDate;

    private LocalDateTime lastUpdatedDate;

    private String location;


//    private Certification certification;

//    @ElementCollection
//    @CollectionTable (
//            name = "menu_list",
//            joinColumns = @JoinColumn(name = "restaurant_id")
//    )
//    @OrderColumn(name = "menu_id")
//    List<Menu> menuList;

    @ElementCollection
    @CollectionTable (
            name = "ImageList",
            joinColumns = @JoinColumn(name = "restaurant_id")
    )
    @OrderColumn(name = "imageId")
    private List<GalleryImage> images;

    public Restaurant(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant(String name, String phoneNum, boolean hasParking) {
        this.restaurantName = name;
        this.phoneNum = phoneNum;
        this.hasParking = hasParking;
        this.resisterDate = LocalDateTime.now();
        this.lastUpdatedDate = LocalDateTime.now();
    }
    public Restaurant(String name, String phoneNum, boolean hasParking, String location){
        this(name, phoneNum, hasParking);
        this.location = location;
    }


}
