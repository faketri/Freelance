package com.hivework.domain.controller;

import com.hivework.domain.dto.request.DeveloperResponseDto;
import com.hivework.domain.dto.request.ProjectRequest;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.orders.Orders;
import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.service.orders.OrdersService;
import com.hivework.domain.service.project.ProjectsService;
import com.hivework.domain.service.project.UserResponseService;
import com.hivework.domain.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
    public Page<Projects> findAll(
            final @RequestParam(name = "number", required = true, defaultValue = "0") Integer pageNumber,
            final @RequestParam(name = "size", required = true, defaultValue = "20") Integer pageSize){
        return projectsService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Projects findById(@PathVariable final Long id){
        return projectsService.findById(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Projects> findByUserId(final @PathVariable("id") Long id,
                                       final @RequestParam(name = "number", required = true, defaultValue = "0") Integer pageNumber,
                                       final @RequestParam(name = "size", required = true, defaultValue = "20") Integer pageSize){
        return projectsService.findByUserId(id, PageRequest.of(pageNumber, pageSize));
    }

    @RequestMapping(value = "/response/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperResponseProjects> usersResponse(final @PathVariable("id") Long id){
        return userResponseService.findByProjectId(id);
    }

    @RequestMapping(value = "/response/{id}/create/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeveloperResponseProjects usersResponseCreate(final @PathVariable("id") Long id,
                                                               final @PathVariable Long userId,
                                                               final @RequestParam String message){
        final Projects projects = projectsService.findById(id);

        if (projects.getUsersCreator().getId().equals(userId))
            throw new RuntimeException("Вы не можете откликнуться на свой заказ.");

        final Users users = userService.findById(userId);

        final DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();

        developerResponseProjects.setProjects(projects);
        developerResponseProjects.setMessage(message);
        developerResponseProjects.setUsersDeveloper(users);

        return userResponseService.save(developerResponseProjects);
    }

    @RequestMapping(value = "/response/{id}/accept/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders userResponseToProject(final @PathVariable("id") Long id, final @PathVariable Long userId){
        final Projects project = projectsService.findById(id);
        final Orders orders = new Orders();
        final Users userDev = userService.findById(userId);

        orders.setProjects(project);
        orders.setDeveloper(userDev);
        orders.setActive(true);

        project.setActive(false);
        projectsService.save(project);

        return ordersService.save(orders);
    }

    @RequestMapping(value = "/response/{id}/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void UsersResponseSave(final @PathVariable("id") Long id, final @RequestBody DeveloperResponseDto developerResponseDto){
        final DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();
        final Projects projects = projectsService.findById(developerResponseProjects.getProjects().getId());
        final Users users = userService.getCurrentUser();

        developerResponseProjects.setProjects(projects);
        developerResponseProjects.setUsersDeveloper(users);
        developerResponseProjects.setMessage(developerResponseDto.getMessage());

        userResponseService.save(developerResponseProjects);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Projects save(final @RequestPart("project") ProjectRequest projectRequest,
                         final @RequestPart(value = "images", required = false) List<MultipartFile> images){
        Projects projects = new Projects();
        final Users users = userService.getCurrentUser();

        projects.setUsersCreator(users);
        projects.setTitle(projectRequest.getTitle());
        projects.setSkills(projectRequest.getSkills());
        projects.setDescription(projectRequest.getDescription());
        projects.setDateOfCompletion(projectRequest.getDateOfCompletion());
        projects.setSubCategories(projectRequest.getSubCategories());
        projects.setPrice(projectRequest.getPrice());

        final String path = "/app/images/";
        
        for (MultipartFile image : images) {
            String imageName = path + projects.getTitle().replace(' ', '-') + "-" + image.getOriginalFilename();
            System.out.println(imageName);
            try {
                image.transferTo(Paths.get(imageName));
            } catch (IOException e) {
                System.out.println(this.getClass() + " " + e.getMessage());
            }
            projects.getImages().add(new Image(null, imageName));
        }

        return projectsService.save(projects);
    }
}
