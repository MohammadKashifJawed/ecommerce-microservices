package com.kashif.product_service.service.impl;

import com.kashif.product_service.dto.CategoryResponse;
import com.kashif.product_service.exception.CategoryNotExistException;
import com.kashif.product_service.mapper.CategoryMapper;
import com.kashif.product_service.model.Category;
import com.kashif.product_service.repository.CategoryRepository;
import com.kashif.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponse createCategory(String name) {
        return mapper.categoryToCategoryResponse(
                repository.save(
                        mapper.categoryNameToCategory(name)
                )
        );
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long id, String name) {
        Category category = repository.findById(id).orElseThrow(
                () -> new CategoryNotExistException("Category with given id not found")
        );
        category.setName(name);
        return mapper.categoryToCategoryResponse(
                repository.save(category)
        );
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return mapper.categoryToCategoryResponse(
                repository.findById(id).orElseThrow(
                        () -> new CategoryNotExistException("Category with given id not found")
                )
        );
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return mapper.categoryListToCategoryResponseList(
                repository.findAll()
        );
    }

    @Override
    public void deleteCategory(Long id) {
        repository.delete(
                repository.findById(id).orElseThrow(
                        () -> new CategoryNotExistException("Category with given id not found")
                )
        );
    }
}
