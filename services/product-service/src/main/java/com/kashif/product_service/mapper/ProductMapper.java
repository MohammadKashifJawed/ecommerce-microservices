package com.kashif.product_service.mapper;

import com.kashif.product_service.dto.ProductRequest;
import com.kashif.product_service.dto.ProductResponse;
import com.kashif.product_service.exception.CategoryNotExistException;
import com.kashif.product_service.model.Category;
import com.kashif.product_service.model.Product;
import com.kashif.product_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Product productRequestToProduct(ProductRequest request, Long categoryId){
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .categoryId(categoryId)
                .brand(request.getBrand())
                .imageUrl(request.getImageUrl())
                .build();
    }

    public ProductResponse productToProductResponse(Product product){
        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(
                () -> new CategoryNotExistException("Category with id not found")
        );
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(categoryMapper.categoryToCategoryResponse(category))
                .brand(product.getBrand())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public List<ProductResponse> productListToProductResponseList(List<Product> productList){
        List<ProductResponse> responses = new ArrayList<>();
        for (Product product : productList){
            Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(
                    () -> new CategoryNotExistException("Category with id not found")
            );
            responses.add(
                    ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .stock(product.getStock())
                            .category(categoryMapper.categoryToCategoryResponse(category))
                            .brand(product.getBrand())
                            .imageUrl(product.getImageUrl())
                            .build()
            );
        }
        return responses;
    }
}