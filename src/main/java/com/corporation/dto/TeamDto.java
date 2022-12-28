package com.corporation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Команда проекта")
@Data
@Builder
public class TeamDto {
    @Schema(description = "Уникальный идентификатор команды")
    private long id;
    @Schema(description = "Название команды")
    private String title;
    @Schema(description = "Описание команды")
    private String description;
    @Schema(description = "ID Проекта")
    private long projectId;
}
