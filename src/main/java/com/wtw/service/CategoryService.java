package com.wtw.service;

import com.wtw.domain.Category;
import com.wtw.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findCategoryById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
