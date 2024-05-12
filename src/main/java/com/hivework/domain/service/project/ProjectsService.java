package com.hivework.domain.service.project;

import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.repository.ProjectsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;


    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Projects findById(Long id){
        return projectsRepository.findById(id).orElse(null);
    }

    public Page<Projects> findByUserId(Long id, Pageable pageable){
        return projectsRepository.findByUsersCreator_Id(id, pageable);
    }

    public Page<Projects> findAll(Pageable pageable){
        return projectsRepository.findAll(pageable);
    }

    public Projects save(Projects projects){
        return projectsRepository.save(projects);
    }
}
