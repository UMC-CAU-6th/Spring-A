package com.umc.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDTO {
    String content;
    Integer readNum;
    Integer goodNum;
    Long MemberId;
    Long BoardId;
    Long PostId;
}
