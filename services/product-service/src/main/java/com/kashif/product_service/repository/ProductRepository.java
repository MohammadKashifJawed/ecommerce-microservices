package com.kashif.product_service.repository;

import com.kashif.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT p FROM Product p
        WHERE (p.categoryId IS NULL OR p.categoryId = :category)
        OR (p.price IS NULL OR p.price >= :minPrice)
        OR (p.price IS NULL OR p.price <= :maxPrice)
    """)
    List<Product> filterProducts(
            @Param("category") Long category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}
