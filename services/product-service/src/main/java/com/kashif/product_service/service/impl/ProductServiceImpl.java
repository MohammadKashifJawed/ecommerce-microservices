package com.kashif.product_service.service.impl;

import com.kashif.product_service.dto.ProductRequest;
import com.kashif.product_service.dto.ProductResponse;
import com.kashif.product_service.exception.CategoryNotExistException;
import com.kashif.product_service.exception.ProductNotExistException;
import com.kashif.product_service.mapper.ProductMapper;
import com.kashif.product_service.model.Category;
import com.kashif.product_service.model.Product;
import com.kashif.product_service.repository.CategoryRepository;
import com.kashif.product_service.repository.ProductRepository;
import com.kashif.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategory()).orElseThrow(
                () -> new CategoryNotExistException("Category with provided name not found " +
                        "Please create category first then add products to it")
        );
        return mapper.productToProductResponse(
                repository.save(
                        mapper.productRequestToProduct(request, category.getId())
                )
        );
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(ProductRequest request, Long id) {
        Category category = categoryRepository.findByName(request.getCategory()).orElseThrow(
                () -> new CategoryNotExistException("Category with provided name not found")
        );
        Product product = repository.findById(id).orElseThrow(
                () -> new ProductNotExistException("Product with provided id not found")
        );
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setBrand(request.getBrand());
        product.setImageUrl(request.getImageUrl());
        product.setCategoryId(category.getId());
        return mapper.productToProductResponse(
                repository.save(product)
        );
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return mapper.productToProductResponse(
                repository.findById(id).orElseThrow(
                        () -> new ProductNotExistException("Product with given id not found")
                )
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return mapper.productListToProductResponseList(
                repository.findAll()
        );
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ProductNotExistException("Product with given id not found")
        );
        repository.delete(product);
    }

    @Override
    public List<ProductResponse> getProductsByFilter
            (Long categoryId, String brand, BigDecimal minPrice, BigDecimal maxPrice)
    {
        return mapper.productListToProductResponseList(
                repository.filterProducts(categoryId, brand, minPrice, maxPrice)
        );
    }
}
