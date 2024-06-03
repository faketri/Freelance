package com.hivework.domain.dto.response;

import com.hivework.domain.entity.projects.Projects;

import java.time.LocalDateTime;

public class OrdersResponseDto {

    private Long id;
    private Projects projects;
    private UserResponse developer;
    private PaymentsResponseDto payments;
    private Boolean isActive = true;
    private LocalDateTime dateOfCreate;

    public OrdersResponseDto() {
    }

    public OrdersResponseDto(Long id, Projects projects, UserResponse developer, PaymentsResponseDto payments, Boolean isActive, LocalDateTime dateOfCreate) {
        this.id = id;
        this.projects = projects;
        this.developer = developer;
        this.payments = payments;
        this.isActive = isActive;
        this.dateOfCreate = dateOfCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public UserResponse getDeveloper() {
        return developer;
    }

    public void setDeveloper(UserResponse developer) {
        this.developer = developer;
    }

    public PaymentsResponseDto getPayments() {
        return payments;
    }

    public void setPayments(PaymentsResponseDto payments) {
        this.payments = payments;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
