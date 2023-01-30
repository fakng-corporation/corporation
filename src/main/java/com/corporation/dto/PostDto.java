package com.corporation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PostDto {

    private long id;
    private String title;
    private String body;
    private boolean published;
    private long userId;
    private long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
