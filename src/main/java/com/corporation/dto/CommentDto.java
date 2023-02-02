package com.corporation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private long id;
    private LocalDateTime createdDate;
    private UserDto user;
    private PostDto post;
    private String content;
}
