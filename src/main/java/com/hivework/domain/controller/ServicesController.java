package com.hivework.domain.controller;


import com.hivework.domain.dto.request.ServiceRequest;
import com.hivework.domain.dto.response.ServiceResponseDto;
import com.hivework.domain.mapper.ServicesMapper;
import com.hivework.domain.service.services.ServicesService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/services")
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceResponseDto> findAll() {
        return servicesService.findAll().stream().map(ServicesMapper::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseDto findById(final @PathVariable("id") Long id) {
        return ServicesMapper.toDto(servicesService.findById(id));
    }

    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceResponseDto> findAll(final @PathVariable("user_id") Long id) {
        return servicesService.findByUserId(id).stream().map(ServicesMapper::toDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceResponseDto save(final @RequestPart("service") @Valid ServiceRequest serviceRequest,
                                   final @RequestPart("images") List<MultipartFile> images) {
        return ServicesMapper.toDto(servicesService.create(serviceRequest, images));
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(final @PathVariable("id") Long id) {
        servicesService.deleteById(id);
    }
}
