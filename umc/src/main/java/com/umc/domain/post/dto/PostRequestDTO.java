package com.umc.domain.post.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class PostRequestDTO {
    @Column(name = "Content", columnDefinition = "TEXT")
    private String content;

    MultipartFile postPicture;
}