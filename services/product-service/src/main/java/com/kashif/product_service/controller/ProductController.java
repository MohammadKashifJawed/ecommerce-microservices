package com.kashif.product_service.controller;

import com.kashif.product_service.dto.ApiResponse;
import com.kashif.product_service.dto.ProductRequest;
import com.kashif.product_service.dto.ProductResponse;
import com.kashif.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "Product created successfully",
                service.addProduct(request)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @RequestBody ProductRequest request, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "Product updated successfully",
                service.updateProduct(request, Long.valueOf(id))
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "Product fetched successfully",
                service.getProductById(Long.valueOf(id))
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "All Products fetched successfully",
                service.getAllProducts()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable String id){
        service.deleteProduct(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                "Product deleted successfully",
                null
        ));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> filterProducts(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice
    ){
        Long category = null;
        BigDecimal min = null, max = null;
        if (categoryId != null) category = Long.valueOf(categoryId);
        if (minPrice != null) min = BigDecimal.valueOf(Double.parseDouble(minPrice));
        if (maxPrice != null) max = BigDecimal.valueOf(Double.parseDouble(maxPrice));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Filter applied",
                        service.getProductsByFilter(
                                category,
                                min,
                                max
                        )
                )
        );
    }

}
