package com.umc.domain.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDTO {
    String content;
    Long MemberId;
    Long BoardId;
}
