package com.umc.domain.comment.entity;

import com.umc.common.entity.BaseTimeEntity;
import javax.persistence.*;

import com.umc.domain.board.entity.Board;
import com.umc.domain.post.entity.Post;
import com.umc.domain.user.entity.Member;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String status;
}
