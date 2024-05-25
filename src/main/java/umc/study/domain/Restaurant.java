package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.mapping.MemberMission;

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
    private Integer restaurantId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate receivedDate;

    @Column(nullable = false)
    private MissionStatus status;

    @Column(nullable = false, length = 50)
    private Integer category;

    @Column(nullable = false)
    private Integer rating;

    //-----------------------------------------------------------

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

}