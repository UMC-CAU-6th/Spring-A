package com.umc.domain.Comment.entity;


import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.post.entity.Posts;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Comments")
@AllArgsConstructor
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "Content", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private long parent_id; // 여기서 ManyToOne을 사용해야 하는가???
}
