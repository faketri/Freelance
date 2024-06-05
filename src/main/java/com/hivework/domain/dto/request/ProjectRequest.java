package com.hivework.domain.dto.request;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.skills.Skills;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProjectRequest {
    @NotBlank(message = "Название заказа не может быть пустое")
    private String title;
    @NotBlank(message = "Описание заказа не может быть пустое")
    private String description;
    private SubCategories subCategories;
    @NotBlank(message = "Цена не может быть пустой")
    @Min(value = 1000)
    private Integer price;
    private LocalDateTime dateOfCompletion;
    private Set<Skills> skills = new HashSet<>();

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
