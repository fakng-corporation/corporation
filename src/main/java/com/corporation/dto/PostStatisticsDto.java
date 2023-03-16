package com.corporation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PostStatisticsDto {
    private long id;
    private long postId;
    private long like;
    private long view;
    private long comment;
}
