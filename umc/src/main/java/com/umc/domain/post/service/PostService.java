package com.umc.domain.post.service;

import com.umc.common.aws.s3.AmazonS3Manager;
import com.umc.common.aws.s3.Uuid;
import com.umc.common.aws.s3.UuidRepository;
import com.umc.common.exception.handler.PostHandler;
import com.umc.common.jwt.SecurityUtil;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.domain.Comment.entity.Comments;
import com.umc.domain.Comment.repository.CommentRepository;
import com.umc.domain.post.Converter.postConverter;
import com.umc.domain.post.dto.DeleteResponseDTO;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.Post_CommentDTO;
import com.umc.domain.post.entity.Posts;
import com.umc.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    public Optional<Posts> getPost(Long id){

        Optional<Posts> posts = postRepository.findById(id);

        if(posts.isEmpty()){
            throw new PostHandler(ErrorCode.POST_NOTFOUND);
        }

        return postRepository.findById(id);
    }
    public Post_CommentDTO getPost_Comment(Long id){

        Optional<Posts> post = getPost(id);

        List<Comments> post_comments = commentRepository.findCommentsByParent_id(id);

        Post_CommentDTO DTO = postConverter.CreatePost_Comment(post, post_comments);

        return DTO;
    }

    public Posts writePost(PostRequestDTO request){
        Posts post = postConverter.toPosts(request);

        String uuid = UUID.randomUUID().toString();

        Uuid savedUuid = uuidRepository.save(
                Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), request.getPostPicture());
        post.setPictureURL(pictureUrl);

        return postRepository.save(post);
    }

    public Posts editPost(PostRequestDTO request, Long id) {

        postRepository.findById(id);

        postRepository.deleteById(id);
        Posts newPost = postConverter.toPosts(request);
        postRepository.save(newPost);
        return newPost;
    }

    public DeleteResponseDTO deletePost(Long id){
        postRepository.findById(id);

        postRepository.deleteById(id);
        return postConverter.CreateDeleteDTO(id);
    }

    public Posts likePost(Long postId) {
        Optional<Posts> post = postRepository.findById(postId);
        Long userId = SecurityUtil.getCurrentUser().getId();

        if (post.isPresent()) {
            Posts existingPost = post.get();
            Integer likes = existingPost.getLikes();
            List<Long> likes_list = existingPost.getLikesList();

            if (!likes_list.contains(userId)) {
                existingPost.setLikes(likes + 1);
                likes_list.add(userId);
                postRepository.save(existingPost); // 이렇게 해도 되나?

                return existingPost;
            }
        }

        return null;
    }

}
