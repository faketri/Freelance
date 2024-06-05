package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.ProjectResponseDto;
import com.hivework.domain.dto.response.RatingResponseDto;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.rating.Rating;

public class RatingMapper {

    public static RatingResponseDto toResponse(Rating rating) {
        return new RatingResponseDto(rating.getId(),
                rating.getTitle(),
                UsersMapper.toResponse(rating.getUserTo()),
                UsersMapper.toResponse(rating.getUserFrom()),
                rating.getGrade(),
                rating.getDescription());
    }

    public static Rating toObj(RatingResponseDto ratingResponseDto) {
        return new Rating(
                ratingResponseDto.getId(),
                ratingResponseDto.getTitle(),
                UsersMapper.toObj(ratingResponseDto.getUserTo()),
                UsersMapper.toObj(ratingResponseDto.getUserFrom()),
                ratingResponseDto.getGrade(),
                ratingResponseDto.getDescription()
        );
    }
}
