package com.umc.domain.post.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public ApiResponse<PostResponseDTO> createPost(PostRequestDTO request){
        Post post= Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .goodNum(request.getGoodNum())
                .readNum(request.getReadNum())
                .build();
        postRepository.save(post);

        return ApiResponse.onSuccess(new PostResponseDTO(post));
    }

    @Transactional
    public Long updatePost(Long post_id, PostRequestDTO request) {

        Post post = PostRepository.findById(post_id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + post_id));
        post.updatePost(request.getTitle());
        postRepository.save(post);
        return post_id;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPost() {
        return postRepository.findAllByOrderByUpdatedAtDesc().stream().map(PostResponseDTO::new).toList();
    }

    @Transactional
    public void deletePost(int post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> {
            return new IllegalArgumentException("Post Id를 찾을 수 없습니다!");
        });

        postRepository.deleteById(post_id);


    }
}
