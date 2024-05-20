package umc.spring.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.Food;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Restaurant;
import umc.spring.study.domain.common.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food_Prefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}