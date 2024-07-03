package com.umc.domain.post.dto;

import com.umc.domain.Comment.entity.Comments;
import com.umc.domain.post.entity.Posts;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
public class Post_CommentDTO {
    private Optional<Posts> post;
    private List<Comments> comments;
}
