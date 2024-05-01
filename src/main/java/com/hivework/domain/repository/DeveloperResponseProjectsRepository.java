package com.hivework.domain.repository;

import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperResponseProjectsRepository extends JpaRepository<DeveloperResponseProjects, Long> {
    List<DeveloperResponseProjects> findByProjects_Id(Long id);
}