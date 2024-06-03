package com.hivework.domain.repository;

import com.hivework.domain.entity.projects.Projects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {


    Page<Projects> findByUsersCreator_Id(Long id, Pageable pageable);

    Page<Projects> findByIsActive(Boolean isActive, Pageable pageable);
}