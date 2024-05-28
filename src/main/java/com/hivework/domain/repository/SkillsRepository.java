package com.hivework.domain.repository;

import com.hivework.domain.entity.skills.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Optional<Skills> findByName(String name);
}