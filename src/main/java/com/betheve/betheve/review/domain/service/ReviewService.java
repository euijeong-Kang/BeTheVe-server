package com.betheve.betheve.review.domain.service;

import com.betheve.betheve.member.domain.entity.id.MemberId;
import com.betheve.betheve.review.domain.entity.Review;

import java.util.List;

public interface ReviewService {

    Review postReview(long restaurantId, long memberId, String content, byte score);

    List<Review> getAllReviewsById(long memberId);

    void deleteReview(long memberId, long reviewId);


}
