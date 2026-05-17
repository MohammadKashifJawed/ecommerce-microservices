package com.kashif.product_service.controller;

import com.kashif.product_service.dto.ApiResponse;
import com.kashif.product_service.dto.CategoryResponse;
import com.kashif.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/{name}")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Category created successfully",
                        service.createCategory(name)
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "name") String name
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Category updated successfully",
                        service.updateCategory(Long.valueOf(id), name)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Category fetched successfully",
                        service.getCategoryById(Long.valueOf(id))
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Categories fetched successfully",
                        service.getAllCategories()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable String id){
        service.deleteCategory(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Category deleted successfully",
                        null
                )
        );
    }

}













