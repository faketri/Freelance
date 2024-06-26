package com.hivework.domain.controller;

import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.mapper.UsersMapper;
import com.hivework.domain.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getCurrentUser() {
        return UsersMapper.toResponse(userService.getCurrentUser());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return UsersMapper.toResponse(userService.findById(id));
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll() {
        return userService.findAll().stream().map(UsersMapper::toResponse).collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}/update/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse updateImage(MultipartFile multipartFile) {
        return UsersMapper.toResponse(userService.updateImage(multipartFile));
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(path = "/{id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
