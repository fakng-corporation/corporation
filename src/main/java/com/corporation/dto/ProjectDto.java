package com.corporation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "Сущность проекта")
@Data
public class ProjectDto {

    @JsonProperty( value = "id", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Уникальный идентификатор проекта")
    private long id;
    @Schema(description = "Название проекта")
    private String title;
    @Schema(description = "Описание проекта")
    private String description;
    @JsonProperty( value = "createdAt", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Дата создания")
    private LocalDateTime createdAt;
    @JsonProperty( value = "updatedAt", access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Дата последнего изменения")
    private LocalDateTime updatedAt;

}
