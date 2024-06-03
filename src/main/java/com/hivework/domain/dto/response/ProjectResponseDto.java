package com.hivework.domain.dto.response;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProjectResponseDto {

    private Long id;

    private String title;

    private String description;

    private UserResponse usersCreator;

    private Set<Image> images = new HashSet<>();

    private SubCategories subCategories;
    private Long price;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfCompletion;

    private Set<Skills> skills = new HashSet<>();
    private Boolean isActive = true;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Long id, String title, String description, UserResponse usersCreator, Set<Image> images, SubCategories subCategories, Long price, LocalDateTime dateOfCreate, LocalDateTime dateOfCompletion, Set<Skills> skills, Boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.usersCreator = usersCreator;
        this.images = images;
        this.subCategories = subCategories;
        this.price = price;
        this.dateOfCreate = dateOfCreate;
        this.dateOfCompletion = dateOfCompletion;
        this.skills = skills;
        this.isActive = isActive;
    }

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

    public UserResponse getUsersCreator() {
        return usersCreator;
    }

    public void setUsersCreator(UserResponse usersCreator) {
        this.usersCreator = usersCreator;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
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

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
