package com.corporation.dto;

import lombok.Data;

@Data
public class PostStatisticsDto {
    private long id;
    private long postId;
    private long like;
    private long view;
    private long comment;
}
