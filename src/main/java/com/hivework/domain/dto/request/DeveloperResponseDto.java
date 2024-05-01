package com.hivework.domain.dto.request;

import com.hivework.domain.entity.projects.Projects;

public class DeveloperResponseDto {

    private Projects projects;
    private String message;

    public DeveloperResponseDto(Projects projects, String message) {
        this.projects = projects;
        this.message = message;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
