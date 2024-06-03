package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.ProjectResponseDto;
import com.hivework.domain.entity.projects.Projects;

public class ProjectMapper {

    public static ProjectResponseDto toResponse(Projects projects) {
        return new ProjectResponseDto(projects.getId(),
                projects.getTitle(),
                projects.getDescription(),
                UsersMapper.toResponse(projects.getUsersCreator()),
                projects.getImages(),
                projects.getSubCategories(),
                projects.getPrice(),
                projects.getDateOfCreate(),
                projects.getDateOfCompletion(),
                projects.getSkills(),
                projects.getActive());
    }

    public static Projects toObj(ProjectResponseDto projectResponseDto) {
        return new Projects(
                projectResponseDto.getId(),
                projectResponseDto.getTitle(),
                projectResponseDto.getDescription(),
                UsersMapper.toObj(projectResponseDto.getUsersCreator()),
                projectResponseDto.getImages(),
                projectResponseDto.getSubCategories(),
                projectResponseDto.getPrice(),
                projectResponseDto.getDateOfCreate(),
                projectResponseDto.getDateOfCompletion(),
                projectResponseDto.getSkills(),
                projectResponseDto.getActive());
    }
}
