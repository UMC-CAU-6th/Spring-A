package com.umc.domain.post.entity;


import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.board.entity.Board;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.user.entity.Member;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;

    private String content;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member poster;

    private String status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
