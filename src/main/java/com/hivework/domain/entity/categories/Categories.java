package com.hivework.domain.entity.categories;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SubCategories> subCategories = new HashSet<>();

    public Categories() {
    }

    public Categories(Long id, String name, Set<SubCategories> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
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

    public Set<SubCategories> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
