package com.betheve.betheve.review.domain.service;

import com.betheve.betheve.common.exception.BusinessException;
import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.review.domain.entity.Review;
import com.betheve.betheve.review.domain.entity.dto.PostReviewDto;
import com.betheve.betheve.review.domain.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private final ReviewRepository repo;

    public ReviewServiceImpl(ReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public Review postReview(long restaurantId, Member author, PostReviewDto postReviewDto) {

//        if(repo.existsByMemberAndRestaurant(memberId, restaurantId)) {
//            throw new IllegalArgumentException("가게당 1개의 리뷰만 작성 할 수 있습니다.");
//        }

        Review review = author.PostReview(restaurantId, postReviewDto.getContent(), postReviewDto.getScore());

        repo.save(review);

        return review;
    }

    @Override
    public List<Review> getAllReviewsById(long memberId) {

        return repo.findAllByMemberId(memberId);
    }

    @Override
    public boolean deleteReview(Member owner, long reviewId) throws BusinessException {

        Review review = repo.findByReviewId(reviewId)
                .orElseThrow(() -> new BusinessException("리뷰 정보 없음"));

        if(owner.hasOwnershipOf(review)) {
            repo.delete(review);
            return true;
        }

        return false;
    }

    @Override
    public List<Review> getAllRestaurantReview(long restaurantId) {

        return repo.findAllByRestaurantId(restaurantId);
    }
}
