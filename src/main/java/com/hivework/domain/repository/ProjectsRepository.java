package com.hivework.domain.repository;

import com.hivework.domain.entity.projects.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
}