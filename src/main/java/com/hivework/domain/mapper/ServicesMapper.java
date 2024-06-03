package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.ServiceResponseDto;
import com.hivework.domain.entity.services.Services;

public class ServicesMapper {

    public static ServiceResponseDto toDto(Services services) {
        if (services == null) {
            return null;
        }

        ServiceResponseDto dto = new ServiceResponseDto();
        dto.setId(services.getId());
        dto.setTitle(services.getTitle());
        dto.setDeveloper(UsersMapper.toResponse(services.getDeveloper()));
        dto.setDescription(services.getDescription());
        dto.setSubCategories(services.getSubCategories());
        dto.setImages(services.getImages());
        dto.setSkills(services.getSkills());
        dto.setPrice(services.getPrice());

        return dto;
    }

    public static Services toEntity(ServiceResponseDto dto) {
        if (dto == null) {
            return null;
        }

        Services services = new Services();
        services.setId(dto.getId());
        services.setTitle(dto.getTitle());
        services.setDeveloper(UsersMapper.toObj(dto.getDeveloper()));
        services.setDescription(dto.getDescription());
        services.setSubCategories(dto.getSubCategories());
        services.setImages(dto.getImages());
        services.setSkills(dto.getSkills());
        services.setPrice(dto.getPrice());

        return services;
    }
}
