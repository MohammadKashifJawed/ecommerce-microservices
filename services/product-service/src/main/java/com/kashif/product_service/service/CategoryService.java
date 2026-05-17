package com.kashif.product_service.service;

import com.kashif.product_service.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(String name);

    CategoryResponse updateCategory(Long id, String name);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(Long id);
}
