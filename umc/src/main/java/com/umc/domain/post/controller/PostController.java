package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.service.CommentService;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @CrossOrigin
    @Operation(summary = "글 생성 API")
    @PostMapping("/")
    public ApiResponse<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        return postService.createPost(postRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "글 수정 API")
    @PutMapping("/{post_id}")
    public ApiResponse<PostResponseDTO> updatePost(@PathVariable Long Post_id, @Valid @RequestBody PostRequestDTO PostRequestDTO) {
        return PostService.updatePost(Post_id, PostRequestDTO);
    }
    @CrossOrigin
    @Operation(summary = "글 삭제 API")
    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePost(@PathVariable Long post_id,@Valid @RequestBody PostRequestDTO postRequestDTO) {
        return PostService.deletePost(post_id, postRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "글 전체 조회 API")
    @GetMapping("/")
    public List<PostResponseDTO> getPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        return PostService.getPost();
    }

    @CrossOrigin
    @Operation(summary = "글 조회 API")
    @GetMapping("/{post_id")
    public ApiResponse<PostResponseDTO> findByIdPost(@PathVariable Long post_id) {
        return PostService.findPost(post_id);
    }

}
