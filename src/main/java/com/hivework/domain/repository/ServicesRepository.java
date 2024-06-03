package com.hivework.domain.repository;

import com.hivework.domain.entity.services.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findByDeveloper_Id(Long id);
}