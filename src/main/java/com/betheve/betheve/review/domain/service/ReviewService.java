package com.betheve.betheve.review.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.review.domain.entity.Review;
import com.betheve.betheve.review.domain.entity.dto.PostReviewDto;

import java.util.List;

public interface ReviewService {

    /**
     * 리뷰 등록
     * @param restaurantId
     * @param author
     * @param postReviewDto
     * @return
     */
    Review postReview(long restaurantId, Member author, PostReviewDto postReviewDto);

    /**
     * 사용자 작성 리뷰 항목 조회
     * @param memberId
     * @return
     */
    List<Review> getAllReviewsById(long memberId);

    /**
     * 리뷰 삭제
     * @param owner
     * @param reviewId
     * @return
     */
    boolean deleteReview(Member owner, long reviewId);

    List<Review> getAllRestaurantReview(long restaurantId);

}
