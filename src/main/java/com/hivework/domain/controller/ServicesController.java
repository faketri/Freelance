package com.hivework.domain.controller;

import com.hivework.domain.entity.services.Services;
import com.hivework.domain.service.services.ServicesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/services")
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
