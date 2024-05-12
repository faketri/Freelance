package com.hivework.domain.entity.services;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Users developer;
    private String description;
    @ManyToOne
    @JoinColumn(name = "sub_categories_id")
    private SubCategories subCategories;
    @OneToMany()
    private Set<Image> images = new HashSet<>();
    @ManyToMany
    private Set<Skills> skills = new HashSet<>();

    public SubCategories getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(SubCategories subCategories) {
        this.subCategories = subCategories;
    }

    public Services() {
    }

    public Services(Long id, String title, Users developer, String description, SubCategories subCategories, Set<Image> images, Set<Skills> skills) {
        this.id = id;
        this.title = title;
        this.developer = developer;
        this.description = description;
        this.subCategories = subCategories;
        this.images = images;
        this.skills = skills;
    }

    public Users getDeveloper() {
        return developer;
    }

    public void setDeveloper(Users developer) {
        this.developer = developer;
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
}
