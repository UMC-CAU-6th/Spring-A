package com.umc.domain.comment.entity;

import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.post.entity.Post;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member commenter;

    private String status;
}
