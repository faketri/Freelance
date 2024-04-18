package com.hivework.domain.repository;

import com.hivework.domain.entity.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}