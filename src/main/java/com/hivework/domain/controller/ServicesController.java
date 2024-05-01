package com.hivework.domain.controller;

import com.hivework.domain.entity.services.Services;
import com.hivework.domain.service.services.ServicesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Services> findAll(){
        return servicesService.findAll();
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Services> findAll(@PathVariable("user_id") Long id){
        return servicesService.findByUserId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Services findById(@PathVariable("id") Long id){
        return servicesService.findById(id);
    }
}
