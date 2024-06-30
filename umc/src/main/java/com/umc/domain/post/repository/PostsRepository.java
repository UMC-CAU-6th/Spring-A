package com.umc.domain.post.repository;

import com.umc.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    //Optional<Post> findById(String )
}
