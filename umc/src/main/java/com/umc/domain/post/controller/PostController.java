package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.Service.PostService;
import com.umc.domain.post.dto.PostCreateRequestDTO;
import com.umc.domain.post.dto.PostListResponseDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.PostUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts") // 생성
    public ApiResponse<PostResponseDTO> createPost(@Valid @RequestBody PostCreateRequestDTO postCreateRequestDTO) {
        return postService.createPost(postCreateRequestDTO);
    }

    @DeleteMapping("/posts/{postId}") // 삭제
    public ApiResponse<String> deletePost(@Valid @PathVariable Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/posts") // 전체 및 조건 조회
    public ApiResponse<PostListResponseDTO> getPostList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long posterId,
            @RequestParam(required = false) String status
    ) {
        return postService.searchPosts(title, posterId, status);
    }

    @GetMapping("/boards/{boardId}/posts") // 게시판 내부에서 전체 및 조건 조회
    public ApiResponse<PostListResponseDTO> getPostListInBoard(
            @Valid @PathVariable Long boardId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long posterId,
            @RequestParam(required = false) String status
    ) {
        return postService.searchPostsInBoard(boardId, title, posterId, status);
    }

    @PutMapping("/posts/{postId}")
    public ApiResponse<PostResponseDTO> updatePost(
            @Valid @PathVariable Long postId,
            @Valid @RequestBody PostUpdateRequestDTO postUpdateRequestDTO
    ) {
        return postService.updatePost(postId, postUpdateRequestDTO);
    }
}
