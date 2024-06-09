package com.hivework.domain.service.project;

import com.hivework.domain.dto.request.ProjectRequest;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.ProjectsRepository;
import com.hivework.domain.service.skills.SkillsService;
import com.hivework.domain.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final SkillsService skillsService;
    private final UserService userService;


    public ProjectsService(ProjectsRepository projectsRepository, SkillsService skillsService, UserService userService) {
        this.projectsRepository = projectsRepository;
        this.skillsService = skillsService;
        this.userService = userService;
    }

    public Projects findById(Long id) {
        return projectsRepository.findById(id).orElse(null);
    }

    public List<Projects> findByUserId(Long id) {
        return projectsRepository.findByUsersCreator_Id(id);
    }

    public List<Projects> findAll() {
        return projectsRepository.findByIsActive(true);
    }

    public Projects save(Projects projects) {
        projects.setSkills(projects.getSkills().stream().map(skillsService::save).collect(Collectors.toSet()));
        return projectsRepository.save(projects);
    }

    public Projects create(ProjectRequest projectRequest, List<MultipartFile> images) {
        try {
            System.out.println("Projects save: start");
            Projects projects = new Projects();

            final Users users = userService.getCurrentUser();

            projects.setUsersCreator(users);
            projects.setTitle(projectRequest.getTitle());
            projects.setSkills(projectRequest.getSkills());
            projects.setDescription(projectRequest.getDescription());
            projects.setDateOfCompletion(projectRequest.getDateOfCompletion());
            projects.setSubCategories(projectRequest.getSubCategories());
            projects.setPrice(projectRequest.getPrice().longValue());

            final String path = "/app/images/";

            if (images != null)
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

            return save(projects);
        } catch (Exception ex) {
            System.out.println("Projects save: " + ex.getMessage());
        }
        return null;
    }

    public void deleteById(Long id) {
        projectsRepository.deleteById(id);
    }
}
