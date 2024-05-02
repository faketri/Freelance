package com.hivework.domain.controller;

import com.hivework.domain.dto.request.DeveloperResponseDto;
import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.service.project.ProjectsService;
import com.hivework.domain.service.project.UserResponseService;
import com.hivework.domain.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectsService projectsService;
    private final UserResponseService userResponseService;
    private final UserService userService;

    public ProjectController(ProjectsService projectsService, UserResponseService userResponseService, UserService userService) {
        this.projectsService = projectsService;
        this.userResponseService = userResponseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Projects> findAll(){
        return projectsService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Projects> findByUserId(@PathVariable("id") Long id){
        return projectsService.findByUserId(id);
    }

    @RequestMapping(value = "/response/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperResponseProjects> UsersResponse(@PathVariable("id") Long id){
        return userResponseService.findByProjectId(id);
    }

    @RequestMapping(value = "/response/{id}/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void UsersResponseSave(@PathVariable("id") Long id, @RequestBody DeveloperResponseDto developerResponseDto){
        final DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();
        final Projects projects = projectsService.findById(developerResponseProjects.getProjects().getId());
        final Users users = userService.getCurrentUser();

        developerResponseProjects.setProjects(projects);
        developerResponseProjects.setUsersDeveloper(users);
        developerResponseProjects.setMessage(developerResponseDto.getMessage());

        userResponseService.save(developerResponseProjects);
    }
}
