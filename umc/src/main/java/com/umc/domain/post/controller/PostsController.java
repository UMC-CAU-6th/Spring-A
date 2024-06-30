package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsController {

    private final PostsService postsService;

    @CrossOrigin
    @Operation(summary = "게시글 작성 API")
    @PostMapping("/")
    public ApiResponse<PostResponseDTO> addPosts(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO createdPost = postsService.addPost(postRequestDTO);
        return ApiResponse.onSuccess(createdPost);
    }

    @CrossOrigin
    @Operation(summary = "게시글 수정 API")
    @PostMapping("/{post_id}")
    public ApiResponse<PostResponseDTO> modifyPosts(@PathVariable("post_id") Long postId, @RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO updatedPost = postsService.modifyPost(postId, postRequestDTO);
        return ApiResponse.onSuccess(updatedPost);
    }

    @CrossOrigin
    @Operation(summary = "게시글 목록 읽기 API")
    @GetMapping("/")
    public ApiResponse<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postsService.getAllPosts();
        return ApiResponse.onSuccess(posts);
    }

    @CrossOrigin
    @Operation(summary = "게시글 세부내용 API")
    @GetMapping("/{post_id}")
    public ApiResponse<PostResponseDTO> getPosts(@PathVariable("post_id") Long postId) {
        PostResponseDTO post = postsService.getPost(postId);
        return ApiResponse.onSuccess(post);
    }

    @CrossOrigin
    @Operation(summary = "게시글 삭제 API")
    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePosts(@PathVariable("post_id") Long postId) {
        postsService.deletePost(postId);
        return ApiResponse.onSuccess(null);
    }
}
