package com.corporation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private long id;
    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
