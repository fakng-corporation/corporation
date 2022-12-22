package com.corporation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleDto {

    private long id;
    private String title;
    private String description;
    private long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
