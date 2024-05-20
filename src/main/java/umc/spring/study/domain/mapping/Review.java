package umc.spring.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Restaurant;
import umc.spring.study.domain.common.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(nullable = false, length = 500)
    private String content;

    private Integer likes;

    private Integer star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}