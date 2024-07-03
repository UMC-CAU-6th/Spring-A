package com.umc.domain.post.controller;

import com.umc.common.jwt.JwtTokenProvider;
import com.umc.domain.post.Converter.postConverter;
import com.umc.domain.post.dto.DeleteResponseDTO;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.Post_CommentDTO;
import com.umc.domain.post.entity.Posts;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.post.service.PostService;
import com.umc.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class PostController {

    private final JwtTokenProvider jwtTokenProvider;
    private final PostRepository postRepository;
    private final PostService postService;

    @CrossOrigin
    @Operation(summary = "글 목록 보여주기")
    @GetMapping("/posts/home/{page}")
    public ApiResponse<Page<Posts>> getPosts(@PathVariable("page") Integer page) {
        Page<Posts> posts = postRepository.findPageBy(PageRequest.of(page-1, 5));
        return ApiResponse.onSuccess(posts);
    }

    @CrossOrigin
    @Operation(summary = "특정 글 보여주기 + 댓글 보여주기")
    @GetMapping("/posts/{id}")
    public ApiResponse<Post_CommentDTO> getDetailPage(@PathVariable("id") Long id) {
        Post_CommentDTO post_comment = postService.getPost_Comment(id);
        return ApiResponse.onSuccess(post_comment);
    }

    @CrossOrigin
    @Operation(summary = "특정 글 수정하는 폼 보여주기")
    @GetMapping("/edits/{id}")
    public ApiResponse<Optional<Posts>> getEditPage(@PathVariable Long id) {
        Optional<Posts> post = postService.getPost(id);
        //갖고 온 데이터를 이용해서 HTML에 정보를 보내고, 프론트 엔드 단에서 형식에 맞게 보여주기
        //전송 버튼을 누르면 수정된 정보를 PUT 요청함.
        return ApiResponse.onSuccess(post); //'HTML 페이지 보내주기'
    }

    @CrossOrigin
    @Operation(summary = "글 작성")
    @PostMapping("/posts")
    public ApiResponse<PostResponseDTO> writePost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        Posts post = postService.writePost(postRequestDTO);
        return ApiResponse.onSuccess(postConverter.CreatePostResponseDTO(post));
    }

    @CrossOrigin
    @Operation(summary = "글 수정")
    @PutMapping("/posts/{id}")
    public ApiResponse<PostResponseDTO> editPost(@PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO) {
        Posts post = postService.editPost(postRequestDTO, id);
        return ApiResponse.onSuccess(postConverter.CreatePostResponseDTO(post));
    }

    @CrossOrigin
    @Operation(summary = "글 삭제")
    @DeleteMapping("/posts/{id}")
    public ApiResponse<DeleteResponseDTO> deletePost(@PathVariable Long id) {
        return ApiResponse.onSuccess(postService.deletePost(id));
    }

    @CrossOrigin
    @Operation(summary = "좋아요 누르기")
    @DeleteMapping("/posts/likes/{id}")
    public ApiResponse<Posts> likePost(@PathVariable Long id) {
        return ApiResponse.onSuccess(postService.likePost(id));
    }

}
