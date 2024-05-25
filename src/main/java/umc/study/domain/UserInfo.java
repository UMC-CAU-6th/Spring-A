package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.mapping.MemberAgree;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.domain.enums.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 225)
    private String email;

    @Column(nullable = false, length = 225)
    private String password;

    @Column(nullable = false)
    private LocalDate joinDate;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(nullable = false)
    private Integer point;

    //---------------------------------------------------------------

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}