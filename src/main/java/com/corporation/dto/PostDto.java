package com.corporation.dto;

import com.corporation.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private long id;
    private String title;
    private String body;
    private boolean isPublished;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

}
