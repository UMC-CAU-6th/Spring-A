package com.umc.umcstudy.domain;

import com.umc.umcstudy.domain.enums.Gender;
import com.umc.umcstudy.mapping.Prefers;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    @OneToMany(mappedBy = "user")
    private List<Prefers> prefersList = new ArrayList<>();
}
