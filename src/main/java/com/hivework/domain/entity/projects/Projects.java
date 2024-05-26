package com.hivework.domain.entity.projects;

import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Users usersCreator;
    @OneToMany
    private Set<Image> images = new HashSet<>();
    @ManyToOne
    private SubCategories subCategories;
    private Long price;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfCompletion;
    @ManyToMany
    private Set<Skills> skills = new HashSet<>();
    private Boolean isActive;

    public Projects() {
    }

    public Projects(Long id, String title, String description, Users usersCreator, SubCategories subCategories, Long price, Set<Skills> skills) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.usersCreator = usersCreator;
        this.subCategories = subCategories;
        this.price = price;
        this.skills = skills;
    }

    @PrePersist
    public void onCreate(){
        this.dateOfCreate = LocalDateTime.now();
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

    public Users getUsersCreator() {
        return usersCreator;
    }

    public void setUsersCreator(Users usersCreator) {
        this.usersCreator = usersCreator;
    }

    public SubCategories getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(SubCategories subCategories) {
        this.subCategories = subCategories;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", usersCreator=" + usersCreator +
                ", dateOfCreate=" + dateOfCreate +
                '}';
    }
}
