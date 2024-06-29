package com.umc.domain.comment.service;

import com.umc.domain.comment.dto.CommentRequestDto;
import com.umc.domain.comment.dto.CommentResponseDto;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void createComment(CommentRequestDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .member(member)
                .post(post)
                .build();

        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .postId(comment.getPost().getId())
                        .memberId(comment.getMember().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .postId(comment.getPost().getId())
                .memberId(comment.getMember().getId())
                .build();
    }

    @Transactional
    public void updateComment(Long id, CommentRequestDto request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        commentRepository.deleteById(id);
    }
}
