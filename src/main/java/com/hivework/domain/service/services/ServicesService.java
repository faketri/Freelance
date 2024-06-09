package com.hivework.domain.service.services;

import com.hivework.domain.dto.request.ServiceRequest;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.services.Services;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.ServicesRepository;
import com.hivework.domain.service.skills.SkillsService;
import com.hivework.domain.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;
    private final SkillsService skillsService;
    private final UserService userService;

    public ServicesService(ServicesRepository servicesRepository, SkillsService skillsService, UserService userService) {
        this.servicesRepository = servicesRepository;
        this.skillsService = skillsService;
        this.userService = userService;
    }

    public Services findById(Long id) {
        return servicesRepository.findById(id).orElse(null);
    }

    public List<Services> findByUserId(Long id) {
        return servicesRepository.findByDeveloper_Id(id);
    }

    public List<Services> findAll() {
        return servicesRepository.findAll();
    }

    public Services save(Services services) {
        services.setSkills(services.getSkills().stream().map(skillsService::save).collect(Collectors.toSet()));
        return servicesRepository.save(services);
    }

    public void deleteById(Long id) {
        servicesRepository.deleteById(id);
    }

    public Services create(ServiceRequest serviceRequest, List<MultipartFile> images) {
        try {
            Services services = new Services();
            final Users users = userService.getCurrentUser();

            services.setDeveloper(users);
            services.setTitle(serviceRequest.getTitle());
            services.setSkills(serviceRequest.getSkills());
            services.setDescription(serviceRequest.getDescription());
            services.setSubCategories(serviceRequest.getSubCategories());
            services.setPrice(serviceRequest.getPrice().longValue());

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
        } catch (Exception ex) {
            System.out.println("create: " + ex.getMessage());
        }
        return null;
    }
}
