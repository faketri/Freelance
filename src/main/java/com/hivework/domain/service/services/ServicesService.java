package com.hivework.domain.service.services;

import com.hivework.domain.entity.services.Services;
import com.hivework.domain.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
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
        return servicesRepository.save(services);
    }
}
