package com.corporation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private long id;
    private String title;
    private String description;
    private long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
