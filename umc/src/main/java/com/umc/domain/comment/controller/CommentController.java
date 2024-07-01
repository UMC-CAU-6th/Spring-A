package com.umc.domain.comment.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.service.BoardService;
import com.umc.domain.comment.dto.CommentRequestDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @CrossOrigin
    @Operation(summary = "댓글 생성 API")
    @PostMapping("/")
    public ApiResponse<CommentResponseDTO> createComment(@Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        return commentService.createComment(commentRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "댓글 업데이트 API")
    @PutMapping("/{comment_id}")
    public ApiResponse<CommentResponseDTO> updateComment(@PathVariable Long Comment_id, @Valid @RequestBody CommentRequestDTO CommentRequestDTO) {
        return CommentService.updateComment(Comment_id, CommentRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "댓글 삭제 API")
    @DeleteMapping("/{comment_id}")
    public ApiResponse<Void> deleteComment(@PathVariable Long comment_id,@Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        return CommentService.deleteComment(comment_id, commentRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "댓글 전체 조회 API")
    @GetMapping("/")
    public List<CommentResponseDTO> getComment(@Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        return CommentService.getComment();
    }

    @CrossOrigin
    @Operation(summary = "댓글 조회 API")
    @GetMapping("/{comment_id")
    public ApiResponse<CommentResponseDTO> findByIdComment(@PathVariable Long comment_id) {
        return CommentService.findComment(comment_id);
    }
}
