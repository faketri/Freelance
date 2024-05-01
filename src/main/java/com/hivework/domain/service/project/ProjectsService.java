package com.hivework.domain.service.project;

import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.repository.ProjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;


    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Projects findById(Long id){
        return projectsRepository.findById(id).orElse(null);
    }

    public List<Projects> findByUserId(Long id){
        return projectsRepository.findByUsersCreator_Id(id);
    }

    public List<Projects> findAll(){
        return projectsRepository.findAll();
    }

    public Projects save(Projects projects){
        return projectsRepository.save(projects);
    }
}
