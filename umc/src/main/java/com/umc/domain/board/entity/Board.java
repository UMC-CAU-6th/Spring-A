package com.umc.domain.board.entity;

import com.umc.common.entity.BaseTimeEntity;

import jakarta.persistence.*;

import com.umc.domain.post.entity.Post;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'USE'")
    private BoardStatus status;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
