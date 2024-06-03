package com.hivework.domain.service.project;

import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.repository.DeveloperResponseProjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserResponseService {
    private final DeveloperResponseProjectsRepository developerResponseProjectsRepository;

    public UserResponseService(DeveloperResponseProjectsRepository developerResponseProjectsRepository) {
        this.developerResponseProjectsRepository = developerResponseProjectsRepository;
    }

    public List<DeveloperResponseProjects> findByProjectId(Long projectId) {
        return developerResponseProjectsRepository.findByProjects_Id(projectId);
    }

    public DeveloperResponseProjects save(DeveloperResponseProjects developerResponseProjects) {
        return developerResponseProjectsRepository.save(developerResponseProjects);
    }
}
