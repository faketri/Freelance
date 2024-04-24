package com.hivework.domain.entity.projects;

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
    private String description;
    @ManyToOne
    private Users usersCreator;
    private Long price;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfCompletion;
    @ManyToMany
    private Set<Skills> skills = new HashSet<>();

    public Projects() {
    }

    public Projects(Long id, String description, Users usersCreator, LocalDateTime dateOfCreate, LocalDateTime dateOfCompletion, Set<Skills> skills) {
        this.id = id;
        this.description = description;
        this.usersCreator = usersCreator;
        this.dateOfCreate = dateOfCreate;
        this.dateOfCompletion = dateOfCompletion;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projects projects = (Projects) o;

        if (!id.equals(projects.id)) return false;
        if (!description.equals(projects.description)) return false;
        if (!usersCreator.equals(projects.usersCreator)) return false;
        return dateOfCreate.equals(projects.dateOfCreate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + usersCreator.hashCode();
        result = 31 * result + dateOfCreate.hashCode();
        return result;
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
