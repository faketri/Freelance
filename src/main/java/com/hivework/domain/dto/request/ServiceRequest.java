package com.hivework.domain.dto.request;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.entity.user.Users;
import org.apache.juli.logging.Log;

import java.util.HashSet;
import java.util.Set;

public class ServiceRequest {
    private String title;
    private Users developer;
    private String description;
    private SubCategories subCategories;
    private Set<Skills> skills = new HashSet<>();

    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getDeveloper() {
        return developer;
    }

    public void setDeveloper(Users developer) {
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

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }
}
