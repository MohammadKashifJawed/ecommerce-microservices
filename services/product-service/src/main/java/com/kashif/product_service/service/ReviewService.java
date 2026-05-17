package com.kashif.product_service.service;

import com.kashif.product_service.dto.ReviewRequest;
import com.kashif.product_service.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse addReview(ReviewRequest request);

    ReviewResponse updateReview(ReviewRequest request, Long id);

    ReviewResponse getReviewById(Long id);

    List<ReviewResponse> fetchAllReviews();

    void deleteReview(Long id);

    List<ReviewResponse> filterReviews(Long product, Long user);
}
