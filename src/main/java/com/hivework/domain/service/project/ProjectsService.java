package com.hivework.domain.service.project;

import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.repository.ProjectsRepository;
import com.hivework.domain.repository.SkillsRepository;
import com.hivework.domain.service.skills.SkillsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final SkillsService skillsService;


    public ProjectsService(ProjectsRepository projectsRepository, SkillsService skillsService) {
        this.projectsRepository = projectsRepository;
        this.skillsService = skillsService;
    }

    public Projects findById(Long id){
        return projectsRepository.findById(id).orElse(null);
    }

    public Page<Projects> findByUserId(Long id, Pageable pageable){
        return projectsRepository.findByUsersCreator_Id(id, pageable);
    }

    public Page<Projects> findAll(Pageable pageable){
        return projectsRepository.findByIsActive(true, pageable);
    }

    public Projects save(Projects projects){
        projects.setSkills(projects.getSkills().stream().map(skillsService::save).collect(Collectors.toSet()));
        return projectsRepository.save(projects);
    }
}
