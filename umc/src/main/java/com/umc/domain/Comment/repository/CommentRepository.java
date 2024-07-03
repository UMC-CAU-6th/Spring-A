package com.umc.domain.Comment.repository;

import com.umc.domain.Comment.entity.Comments;
import com.umc.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    @Query("SELECT p FROM Posts p WHERE p.id = :postId")
    List<Comments> findCommentsByParent_id(@Param("postId") Long postId);
}
