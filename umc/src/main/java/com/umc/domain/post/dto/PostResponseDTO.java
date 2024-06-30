package com.umc.domain.post.dto;

import com.umc.domain.comment.dto.CommentResponseDTO;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class PostResponseDTO {
    private Long post_id;
    private Long board_id;
    private Long member_id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDTO> comments;
}
