package com.umc.domain.Comment.service;

import com.umc.domain.Comment.Converter.CommentConverter;
import com.umc.domain.Comment.dto.CommentRequestDTO;
import com.umc.domain.Comment.dto.DeleteResponseDTO;
import com.umc.domain.Comment.entity.Comments;
import com.umc.domain.Comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    public Optional<Comments> getComment(Long id){
        return commentRepository.findById(id);
    }

    public Comments writeComment(CommentRequestDTO request){
        Comments comment = CommentConverter.toComments(request);
        return commentRepository.save(comment);
    }

    public Comments editPost(CommentRequestDTO request, Long id) {
        commentRepository.deleteById(id);
        // 삭제 못했을 경우, 에러 발생 추가하기
        Comments newComment = CommentConverter.toComments(request);
        commentRepository.save(newComment);
        return newComment;
    }

    public DeleteResponseDTO deletePost(Long id){
        commentRepository.deleteById(id);
        //삭제 못했을 경우, 에러 발생 혹은 실패 코드 보내기
        return CommentConverter.CreateDeleteDTO(id);
    }
}
