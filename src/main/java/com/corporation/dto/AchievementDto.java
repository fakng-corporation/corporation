package com.corporation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Достижение проекта")
@Data
@Builder
public class AchievementDto {
    @Schema(description = "Уникальный идентификатор достижения")
    private long id;
    @Schema(description = "Название достижения")
    private String title;
    @Schema(description = "Описание достижения")
    private String description;
    @Schema(description = "ID Проекта")
    private long projectId;
}
