package com.kashif.product_service.repository;

import com.kashif.product_service.dto.ProductResponse;
import com.kashif.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT p FROM Product p
        WHERE (p.categoryId IS NULL OR p.categoryId = :category)
        AND (p.brand IS NULL OR p.brand = :brand)
        AND (p.price IS NULL OR p.price >= :minPrice)
        AND (p.price IS NULL OR p.price <= :maxPrice)
    """)
    List<Product> filterProducts(
            @Param("category") String category,
            @Param("brand") String brand,
            @Param("minPrice") String minPrice,
            @Param("maxPrice") String maxPrice
    );
}
