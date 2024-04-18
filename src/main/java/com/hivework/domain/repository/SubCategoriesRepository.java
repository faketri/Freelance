package com.hivework.domain.repository;

import com.hivework.domain.entity.categories.SubCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoriesRepository extends JpaRepository<SubCategories, Long> {
}