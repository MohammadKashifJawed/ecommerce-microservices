package com.kashif.product_service.repository;

import com.kashif.product_service.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT r FROM Review r
        WHERE r.productId = :product
        OR r.userId = :user
    """)
    List<Review> filterReviews(@Param("product") Long product, @Param("user") Long user);
}
