package com.kashif.product_service.controller;

import com.kashif.product_service.dto.ApiResponse;
import com.kashif.product_service.dto.ReviewRequest;
import com.kashif.product_service.dto.ReviewResponse;
import com.kashif.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> addReview(@RequestBody ReviewRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Review added successfully",
                        service.addReview(request)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(
            @RequestBody ReviewRequest request,
            @PathVariable String id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Review updated successfully",
                        service.updateReview(request, Long.valueOf(id))
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Review fetched successfully",
                        service.getReviewById(Long.valueOf(id))
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviews(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Fetched all reviews",
                        service.fetchAllReviews()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable String id){
        service.deleteReview(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Review deleted sucessfully",
                        null
                )
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> filterReviews(
            @RequestParam(required = false) String productId
    ){
        Long product = 0L;
        if (productId != null) product = Long.parseLong(productId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Fetched all reviews",
                        service.filterReviews(product)
                )
        );
    }
}
