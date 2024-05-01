package com.hivework.domain.controller;

import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.mapper.UsersMapper;
import com.hivework.domain.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable("id") Long id){
        return UsersMapper.toResponse(userService.findById(id));
    }
}
