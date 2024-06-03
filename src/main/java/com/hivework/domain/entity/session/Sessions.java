package com.hivework.domain.entity.session;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String sessionId;
    private String username;
    private LocalDateTime lastAccessTime;
    private LocalDateTime dateOfCreate;

    public Sessions() {
    }

    public Sessions(Long id, String sessionId, String username, LocalDateTime dateOfCreate) {
        this.id = id;
        this.sessionId = sessionId;
        this.username = username;
        this.dateOfCreate = dateOfCreate;
    }

    @PrePersist
    public void onCreate() {
        this.dateOfCreate = LocalDateTime.now();
        this.lastAccessTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastAccessTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}