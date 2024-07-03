package com.umc.domain.post.entity;


import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Getter
@Builder
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Posts")
@AllArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "Content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer Likes;

    @ElementCollection
    @CollectionTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private List<Long> LikesList; // 좋아요 누른 사용자 ID 리스트

    private String pictureURL;
}
