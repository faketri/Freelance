package com.hivework.domain.repository;

import com.hivework.domain.entity.projects.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {


    List<Projects> findByUsersCreator_Id(Long id);

    List<Projects> findByIsActive(Boolean isActive);
}