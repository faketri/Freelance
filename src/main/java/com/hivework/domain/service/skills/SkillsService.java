package com.hivework.domain.service.skills;

import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.repository.SkillsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsService {

    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public Skills findById(Long id) {
        return skillsRepository.findById(id).orElseGet(null);
    }

    public Optional<Skills> findByName(String name) {
        return skillsRepository.findByName(name);
    }

    public Skills save(Skills skills) {
        return findByName(skills.getName()).orElseGet(() -> skillsRepository.save(skills));
    }

    @Transactional
    public List<Skills> save(List<Skills> skills) {
        return skills.stream().map(this::save).collect(Collectors.toList());
    }
}
