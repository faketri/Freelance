package com.hivework.domain.controller;

import com.hivework.domain.dto.request.DeveloperResponseDto;
import com.hivework.domain.dto.request.ProjectRequest;
import com.hivework.domain.dto.response.OrdersResponseDto;
import com.hivework.domain.dto.response.ProjectResponseDto;
import com.hivework.domain.dto.response.UserDeveloperResponseDto;
import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.mapper.OrdersMapper;
import com.hivework.domain.mapper.ProjectMapper;
import com.hivework.domain.mapper.UserDeveloperMapper;
import com.hivework.domain.service.orders.OrdersService;
import com.hivework.domain.service.project.ProjectsService;
import com.hivework.domain.service.project.UserResponseService;
import com.hivework.domain.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectsService projectsService;
    private final UserResponseService userResponseService;
    private final OrdersService ordersService;
    private final UserService userService;

    public ProjectController(ProjectsService projectsService, UserResponseService userResponseService, OrdersService ordersService, UserService userService) {
        this.projectsService = projectsService;
        this.userResponseService = userResponseService;
        this.ordersService = ordersService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectResponseDto> findAll() {
        return projectsService.findAll().stream().map(ProjectMapper::toResponse).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectResponseDto findById(@PathVariable final Long id) {
        return ProjectMapper.toResponse(projectsService.findById(id));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectResponseDto> findByUserId(final @PathVariable("id") Long id) {
        return projectsService.findByUserId(id).stream().map(ProjectMapper::toResponse).collect(Collectors.toList());
    }

    @RequestMapping(value = "/response/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDeveloperResponseDto> usersResponse(final @PathVariable("id") Long id) {
        return userResponseService.findByProjectId(id).stream().map(UserDeveloperMapper::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/response/{id}/create/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDeveloperResponseDto usersResponseCreate(final @PathVariable("id") Long id,
                                                        final @PathVariable Long userId,
                                                        final @RequestParam String message) {
        return UserDeveloperMapper.toDto(userResponseService.create(id, userId, message));
    }

    @RequestMapping(value = "/response/{id}/accept/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrdersResponseDto userResponseToProject(final @PathVariable("id") Long id, final @PathVariable Long userId) {
        return OrdersMapper.toDto(ordersService.create(id, userId));
    }

    @RequestMapping(value = "/response/{id}/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void usersResponseSave(final @PathVariable("id") Long id, final @RequestBody DeveloperResponseDto developerResponseDto) {
        final DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();
        final Projects projects = projectsService.findById(developerResponseProjects.getProjects().getId());
        final Users users = userService.getCurrentUser();

        developerResponseProjects.setProjects(projects);
        developerResponseProjects.setUsersDeveloper(users);
        developerResponseProjects.setMessage(developerResponseDto.getMessage());

        userResponseService.save(developerResponseProjects);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjectResponseDto save(final @RequestPart("project") @Valid ProjectRequest projectRequest,
                                   final @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        return ProjectMapper.toResponse(projectsService.create(projectRequest, images));
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(final @PathVariable("id") Long id) {
        projectsService.deleteById(id);
    }
}
