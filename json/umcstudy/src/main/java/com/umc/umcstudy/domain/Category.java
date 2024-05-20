package com.umc.umcstudy.domain;

import com.umc.umcstudy.mapping.Prefers;
import com.umc.umcstudy.mapping.RestaurantCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<RestaurantCategory> restaurantCategoryList;

    @OneToMany(mappedBy = "category")
    private List<Prefers> prefersList;
}
