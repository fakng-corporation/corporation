package com.corporation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDraftPostDto {
    private Long id;
    private String title;
    private String body;
}