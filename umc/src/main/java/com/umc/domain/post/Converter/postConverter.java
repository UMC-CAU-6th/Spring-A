package com.umc.domain.post.Converter;

import com.umc.common.jwt.SecurityUtil;
import com.umc.domain.Comment.entity.Comments;
import com.umc.domain.post.dto.DeleteResponseDTO;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.Post_CommentDTO;
import com.umc.domain.post.entity.Posts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class postConverter { // 로그인 안 한 경우에 대한 예외 처리 추가 =

    LocalDateTime now = LocalDateTime.now();

    public static Posts toPosts(PostRequestDTO request){
        return Posts.builder()
        .content(request.getContent())
                .Likes(0)
        .member(SecurityUtil.getCurrentUser()).build();
    }

    public static PostResponseDTO CreatePostResponseDTO(Posts post){
        return PostResponseDTO.builder()
                .postId(post.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static DeleteResponseDTO CreateDeleteDTO(Long id){

        return DeleteResponseDTO.builder()
                .postId(id)
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static Post_CommentDTO CreatePost_Comment(Optional<Posts> post, List<Comments> post_comments){

        return Post_CommentDTO.builder()
                .post(post)
                .comments(post_comments)
                .build();
    }
}
