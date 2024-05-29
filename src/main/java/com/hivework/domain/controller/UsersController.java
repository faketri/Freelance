package com.hivework.domain.controller;

import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.mapper.UsersMapper;
import com.hivework.domain.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getCurrentUser(){
        return UserMapper.toResponse(userService.getCurrentUser());
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable("id") Long id){
        return UsersMapper.toResponse(userService.findById(id));
    }
}
