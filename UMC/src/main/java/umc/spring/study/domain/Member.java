package umc.spring.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.common.BaseEntity;
import umc.spring.study.domain.enums.Gender;
import umc.spring.study.domain.enums.SocialType;
import umc.spring.study.domain.mapping.Comment;
import umc.spring.study.domain.mapping.Food_Prefer;
import umc.spring.study.domain.mapping.OngoingMission;
import umc.spring.study.domain.mapping.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 50)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private LocalDate inactiveDate;

    @Column(nullable = false, length = 20)
    private String email;

    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> CommentList = new ArrayList<>();
    /*
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Food_Prefer> Food_PreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<OngoingMission> OngoingMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> ReviewList = new ArrayList<>();*/
}