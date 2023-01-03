package com.corporation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Сущность проекта")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
