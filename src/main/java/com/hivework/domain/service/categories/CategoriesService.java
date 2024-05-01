package com.hivework.domain.service.categories;

import com.hivework.domain.entity.categories.Categories;
import com.hivework.domain.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public Categories findById(Long id){
        return categoriesRepository.findById(id).orElse(null);
    }

    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }

    public Categories save(Categories categories){
        return categoriesRepository.save(categories);
    }

    public List<Categories> save(List<Categories> categories){
        return categoriesRepository.saveAll(categories);
    }
}
