package com.corporation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Сущность проекта")
@Data
public class ProjectDto {

    @Schema(description = "Уникальный идентификатор проекта")
    private long id;
    @Schema(description = "Название проекта")
    private String title;
    @Schema(description = "Описание проекта")
    private String description;
    @Schema(description = "Дата создания")
    private LocalDateTime createdAt;
    @Schema(description = "Дата последнего изменения")
    private LocalDateTime updatedAt;
    @Schema(description = "ID Владелеца")
    private long ownerId;

}
