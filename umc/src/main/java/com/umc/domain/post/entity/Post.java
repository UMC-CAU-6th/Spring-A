package com.umc.domain.post.entity;


import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.board.entity.Board;
import com.umc.domain.user.entity.Member;
import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;

    private String content;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String status;
}
