package com.corporation.mapper;

import com.corporation.dto.ReviewDto;
import com.corporation.model.Review;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);
}