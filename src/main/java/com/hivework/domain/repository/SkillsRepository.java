package com.hivework.domain.repository;

import com.hivework.domain.entity.skills.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
}