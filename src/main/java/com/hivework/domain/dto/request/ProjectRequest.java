package com.hivework.domain.dto.request;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.skills.Skills;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProjectRequest {

    private Long id;
    private String title;
    private String description;
    private SubCategories subCategories;
    private Long price;
    private LocalDateTime dateOfCompletion;
    private Set<Skills> skills = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubCategories getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(SubCategories subCategories) {
        this.subCategories = subCategories;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDateTime dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }
}
