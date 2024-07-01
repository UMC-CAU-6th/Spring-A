package com.umc.domain.post.dto;

import com.umc.domain.comment.entity.Comment;
import com.umc.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    Long PostId;
    String title;
    LocalDateTime created_at;
    LocalDateTime updated_at;
    Long MemberId;
    Long BoardId;
    String content;
    Integer readNum;
    Integer goodNum;

    public PostResponseDTO(Post entity)
    {
        this.PostId=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.readNum=entity.getReadNum();
        this.goodNum=entity.getGoodNum();
        this.created_at=entity.getCreatedAt();
        this.updated_at=entity.getUpdatedAt();
    }
}
