package com.hivework.domain.controller;

import com.hivework.domain.dto.request.ProjectRequest;
import com.hivework.domain.dto.request.ServiceRequest;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.services.Services;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.service.services.ServicesService;
import com.hivework.domain.service.user.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/services")
public class ServicesController {

    private final ServicesService servicesService;
    private final UserService userService;

    public ServicesController(ServicesService servicesService, UserService userService) {
        this.servicesService = servicesService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Services> findAll(){
        return servicesService.findAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Services findById(final @PathVariable("id") Long id){
        return servicesService.findById(id);
    }
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Services> findAll(final @PathVariable("user_id") Long id){
        return servicesService.findByUserId(id);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Services save(final @RequestPart("service") ServiceRequest serviceRequest,
                         final @RequestPart("images") List<MultipartFile> images){
        Services services = new Services();
        final Users users = userService.getCurrentUser();

        services.setDeveloper(users);
        services.setTitle(serviceRequest.getTitle());
        services.setSkills(serviceRequest.getSkills());
        services.setDescription(serviceRequest.getDescription());
        services.setSubCategories(serviceRequest.getSubCategories());

        final String path = "/app/images/";
        
        for (MultipartFile image : images) {
            String imageName = path + services.getTitle().replace(' ', '-') + "-" + image.getOriginalFilename();
            System.out.println(imageName);
            try {
                image.transferTo(Paths.get(imageName));
            } catch (IOException e) {
                System.out.println(this.getClass() + " " + e.getMessage());
            }
            services.getImages().add(new Image(null, imageName));
        }

        return servicesService.save(services);
    }
}
