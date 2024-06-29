package com.umc.domain.post.controller;

import com.umc.domain.post.dto.PostRequestDto;
import com.umc.domain.post.dto.PostResponseDto;
import com.umc.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void createPost(@RequestBody PostRequestDto request) {
        postService.createPost(request);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable("post_id") Long postId) {
        PostResponseDto post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{post_id}")
    public void updatePost(@PathVariable("post_id") Long postId, @RequestBody PostRequestDto request) {
        postService.updatePost(postId, request);
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable("post_id") Long postId) {
        postService.deletePost(postId);
    }
}
