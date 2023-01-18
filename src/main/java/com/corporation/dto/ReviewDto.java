package com.corporation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Setter
@Builder
public class ReviewDto {
    private long id;
    private String title;
    private String review;
}
