package umc.spring.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.common.BaseEntity;
import umc.spring.study.domain.enums.Gender;
import umc.spring.study.domain.enums.SocialType;
import umc.spring.study.domain.mapping.Food_Prefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    /*@OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Food_Prefer> memberAgreeList = new ArrayList<>();*/
}