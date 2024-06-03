package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.UserDeveloperResponseDto;
import com.hivework.domain.entity.projects.DeveloperResponseProjects;

public class UserDeveloperMapper {

    public static UserDeveloperResponseDto toDto(DeveloperResponseProjects developerResponseProjects) {
        if (developerResponseProjects == null) {
            return null;
        }

        UserDeveloperResponseDto dto = new UserDeveloperResponseDto();
        dto.setId(developerResponseProjects.getId());
        dto.setUsersDeveloper(UsersMapper.toResponse(developerResponseProjects.getUsersDeveloper()));
        dto.setMessage(developerResponseProjects.getMessage());

        return dto;
    }

    public static DeveloperResponseProjects toEntity(UserDeveloperResponseDto dto) {
        if (dto == null) {
            return null;
        }

        DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();
        developerResponseProjects.setId(dto.getId());
        developerResponseProjects.setUsersDeveloper(UsersMapper.toObj(dto.getUsersDeveloper()));
        developerResponseProjects.setMessage(dto.getMessage());

        return developerResponseProjects;
    }
}
