package com.hivework.domain.service.project;

import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.DeveloperResponseProjectsRepository;
import com.hivework.domain.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserResponseService {
    private final DeveloperResponseProjectsRepository developerResponseProjectsRepository;
    private final ProjectsService projectsService;
    private final UserService userService;

    public UserResponseService(DeveloperResponseProjectsRepository developerResponseProjectsRepository, ProjectsService projectsService, UserService userService) {
        this.developerResponseProjectsRepository = developerResponseProjectsRepository;
        this.projectsService = projectsService;
        this.userService = userService;
    }

    public List<DeveloperResponseProjects> findByProjectId(Long projectId) {
        return developerResponseProjectsRepository.findByProjects_Id(projectId);
    }

    public DeveloperResponseProjects save(DeveloperResponseProjects developerResponseProjects) {
        return developerResponseProjectsRepository.save(developerResponseProjects);
    }

    public DeveloperResponseProjects create(final Long id,
                                            final Long userId,
                                            final String message) {
        final Projects projects = projectsService.findById(id);

        if (projects.getUsersCreator().getId().equals(userId))
            throw new RuntimeException("Вы не можете откликнуться на свой заказ.");

        final Users users = userService.findById(userId);

        final DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();

        developerResponseProjects.setProjects(projects);
        developerResponseProjects.setMessage(message);
        developerResponseProjects.setUsersDeveloper(users);

        return developerResponseProjectsRepository.save(developerResponseProjects);
    }
}
