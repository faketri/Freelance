package com.hivework.domain.entity.services;

import com.hivework.domain.entity.image.Image;
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
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Users developer;
    private String description;
    @OneToMany()
    private Set<Image> images = new HashSet<>();

    public Services() {
    }

    public Services(Long id, Users developer, String description, Set<Image> images) {
        this.id = id;
        this.developer = developer;
        this.description = description;
        this.images = images;
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
}
