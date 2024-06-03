package com.hivework.domain.dto.response;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;

import java.util.HashSet;
import java.util.Set;

public class ServiceResponseDto {
    private Long id;
    private String title;
    private UserResponse developer;
    private String description;

    private SubCategories subCategories;

    private Set<Image> images = new HashSet<>();

    private Set<Skills> skills = new HashSet<>();

    private Long price;

    public ServiceResponseDto() {
    }

    public ServiceResponseDto(Long id, String title, UserResponse developer, String description, SubCategories subCategories, Set<Image> images, Set<Skills> skills, Long price) {
        this.id = id;
        this.title = title;
        this.developer = developer;
        this.description = description;
        this.subCategories = subCategories;
        this.images = images;
        this.skills = skills;
        this.price = price;
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

    public UserResponse getDeveloper() {
        return developer;
    }

    public void setDeveloper(UserResponse developer) {
        this.developer = developer;
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
