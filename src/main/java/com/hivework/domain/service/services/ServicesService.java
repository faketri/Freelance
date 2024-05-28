package com.hivework.domain.service.services;

import com.hivework.domain.entity.services.Services;
import com.hivework.domain.repository.ServicesRepository;
import com.hivework.domain.service.skills.SkillsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;
     private final SkillsService skillsService;

    public ServicesService(ServicesRepository servicesRepository, SkillsService skillsService) {
        this.servicesRepository = servicesRepository;
        this.skillsService = skillsService;
    }

    public Services findById(Long id){
        return servicesRepository.findById(id).orElse(null);
    }

    public List<Services> findByUserId(Long id){
        return servicesRepository.findByDeveloper_Id(id);
    }

    public List<Services> findAll(){
        return servicesRepository.findAll();
    }

    public Services save(Services services){
        services.setSkills(services.getSkills().stream().map(skillsService::save).collect(Collectors.toSet()));
        return servicesRepository.save(services);
    }
}
