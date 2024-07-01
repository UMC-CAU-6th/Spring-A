package com.umc.domain.comment.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public ApiResponse<CommentResponseDTO> createComment(CommentRequestDTO request){
        Comment comment=Comment.builder()
                .content(request.getContent())
                .goodNum(request.getGoodNum())
                .readNum(request.getReadNum())
                .build();
        commentRepository.save(comment);

        return ApiResponse.onSuccess(new CommentResponseDTO(comment));
    }

    @Transactional
    public Long updateComment(Long comment_id, CommentRequestDTO request) {

        Comment comment = CommentRepository.findById(comment_id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + comment_id));
        comment.updateComment(request.getContent());
        commentRepository.save(comment);
        return comment_id;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getComment() {
        return commentRepository.findAllByOrderByUpdatedAtDesc().stream().map(CommentResponseDTO::new).toList();
    }

    @Transactional
    public void deleteComment(int comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> {
            return new IllegalArgumentException("Comment Id를 찾을 수 없습니다!");
        });

        commentRepository.deleteById(comment_id);


    }


}
