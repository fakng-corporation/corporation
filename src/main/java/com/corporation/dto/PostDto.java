package com.corporation.dto;

import com.corporation.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private long id;
    private String title;
    private String description;
    private boolean published;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
