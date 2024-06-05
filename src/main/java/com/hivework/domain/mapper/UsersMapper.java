package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.entity.user.Users;

public class UsersMapper {

    public static UserResponse toResponse(Users users) {
        return new UserResponse(users.getId(),
                users.getLogin(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                users.getProfileImage(),
                users.getTelegramUrl(),
                users.getSkills(),
                users.getRoles(),
                users.getDateOfCreate());
    }

    public static Users toObj(UserResponse users) {
        return new Users(users.getId(),
                users.getTelegramUrl(),
                users.getLogin(),
                users.getEmail(),
                users.getFirstName(),
                users.getLastName(),
                null,
                users.getProfileImage(),
                users.getSkills(),
                users.getRoles(),
                users.getDateOfCreate());
    }
}
