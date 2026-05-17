package com.kashif.product_service.mapper;

import com.kashif.product_service.dto.CategoryResponse;
import com.kashif.product_service.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category categoryNameToCategory(String name){
        return Category.builder()
                .name(name)
                .build();
    }

    public CategoryResponse categoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryResponse> categoryListToCategoryResponseList(List<Category> categories){
        List<CategoryResponse> responses = new ArrayList<>();
        for(Category category : categories){
            responses.add(
                    CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build()
            );
        }
        return responses;
    }
}
