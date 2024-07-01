package com.umc.domain.comment.dto;

import com.umc.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    Long CommentId;
    LocalDateTime created_at;
    LocalDateTime updated_at;
    Long MemberId;
    Long BoardId;
    Long PostId;
    String content;
    Integer readNum;
    Integer goodNum;

    public CommentResponseDTO(Comment entity)
    {
        this.CommentId=entity.getId();
        //this.MemberId=entity.getMemberId();
        this.content=entity.getContent();
        this.readNum=entity.getReadNum();
        this.goodNum=entity.getGoodNum();
        this.created_at=entity.getCreatedAt();
        this.updated_at=entity.getUpdatedAt();
    }
}
