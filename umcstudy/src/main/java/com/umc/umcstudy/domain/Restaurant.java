package com.umc.umcstudy.domain;

import com.umc.umcstudy.mapping.RestaurantCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<Mission> missionList;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantCategory> restaurantCategoryList;
}
