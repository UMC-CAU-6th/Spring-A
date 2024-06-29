package com.umc.domain.post.dto;

import com.umc.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Integer likes;
    private String status;
    private Long boardId;
    private Long posterId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    //private comments;

    public PostResponseDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        likes = post.getLikes();
        status = post.getStatus();
        boardId = post.getBoard().getId();
        posterId = post.getPoster().getId();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
}