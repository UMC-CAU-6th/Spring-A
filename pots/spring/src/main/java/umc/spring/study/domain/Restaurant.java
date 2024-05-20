package umc.spring.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.common.BaseEntity;
import umc.spring.study.domain.enums.RestaurantStatus;
import umc.spring.study.domain.mapping.MemberPrefer;
import umc.spring.study.domain.mapping.MemberReview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    //@Column(name = "start_time", nullable = false, columnDefinition = "DATE")
    private LocalDate startTime;

    //@Column(name = "end_time", nullable = false, columnDefinition = "DATE")
    private LocalDate endTime;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus restaurantStatus;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MemberReview> memberReviewList = new ArrayList<>();
}
