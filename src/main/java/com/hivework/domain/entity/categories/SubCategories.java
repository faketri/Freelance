package com.hivework.domain.entity.categories;

import jakarta.persistence.*;

@Entity
public class SubCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;

    public SubCategories() {
    }

    public SubCategories(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubCategories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
