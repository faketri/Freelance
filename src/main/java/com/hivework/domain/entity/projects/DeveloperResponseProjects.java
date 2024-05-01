package com.hivework.domain.entity.projects;

import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

@Entity
public class DeveloperResponseProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    private Projects projects;
    @OneToOne
    private Users usersDeveloper;
    @Column(nullable = false)
    private String message;

    public DeveloperResponseProjects() {
    }

    public DeveloperResponseProjects(Long id, Projects projects, Users usersDeveloper, String message) {
        this.id = id;
        this.projects = projects;
        this.usersDeveloper = usersDeveloper;
        this.message = message;
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

    public Users getUsersDeveloper() {
        return usersDeveloper;
    }

    public void setUsersDeveloper(Users usersDeveloper) {
        this.usersDeveloper = usersDeveloper;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
