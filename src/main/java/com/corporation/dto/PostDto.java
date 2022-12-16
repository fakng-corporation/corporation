package com.corporation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private long id;
    private String title;
    private String body;
    private boolean isPublished;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

}
