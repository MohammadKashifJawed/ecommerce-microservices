package com.kashif.product_service.mapper;

import com.kashif.product_service.dto.ReviewRequest;
import com.kashif.product_service.dto.ReviewResponse;
import com.kashif.product_service.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public ReviewResponse reviewToReviewResponse(Review review){
        return ReviewResponse.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .userId(review.getUserId())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

    public List<ReviewResponse> reviewListToReviewResponseList(List<Review> reviews){
        List<ReviewResponse> responses = new ArrayList<>();
        for (Review review : reviews){
            responses.add(
                    ReviewResponse.builder()
                            .id(review.getId())
                            .productId(review.getProductId())
                            .userId(review.getUserId())
                            .rating(review.getRating())
                            .comment(review.getComment())
                            .build()
            );
        }
        return responses;
    }

    public Review requestToReview(ReviewRequest request){
        return Review.builder()
                .productId(request.getProductId())
                .userId(request.getUserId())
                .rating(request.getRating())
                .comment(request.getComment())
                .build();
    }
}
