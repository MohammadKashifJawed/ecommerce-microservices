package com.kashif.product_service.service.impl;

import com.kashif.product_service.dto.ReviewRequest;
import com.kashif.product_service.dto.ReviewResponse;
import com.kashif.product_service.exception.ReviewNotFoundException;
import com.kashif.product_service.mapper.ReviewMapper;
import com.kashif.product_service.model.Review;
import com.kashif.product_service.repository.ReviewRepository;
import com.kashif.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    @Override
    public ReviewResponse addReview(ReviewRequest request) {

        return mapper.reviewToReviewResponse(
                repository.save(
                        mapper.requestToReview(request)
                )
        );
    }

    @Override
    public ReviewResponse updateReview(ReviewRequest request, Long id) {
        Review review = repository.findById(id).orElseThrow(
                () -> new ReviewNotFoundException("Review with given id not found")
        );
        review.setProductId(request.getProductId());
        review.setUserId(request.getUserId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return mapper.reviewToReviewResponse(
                repository.save(review)
        );
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        return mapper.reviewToReviewResponse(
                repository.findById(id).orElseThrow(
                        () -> new ReviewNotFoundException("Review with given id not found")
                )
        );
    }

    @Override
    public List<ReviewResponse> fetchAllReviews() {
        return mapper.reviewListToReviewResponseList(
                repository.findAll()
        );
    }

    @Override
    public void deleteReview(Long id) {
        repository.delete(
                repository.findById(id).orElseThrow(
                        () -> new ReviewNotFoundException("Review with given id not found")
                )
        );
    }

    @Override
    public List<ReviewResponse> filterReviews(Long product) {
        return mapper.reviewListToReviewResponseList(
                repository.filterReviews(product)
        );
    }
}
















