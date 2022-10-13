package com.betheve.betheve.review.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.id.MemberId;
import com.betheve.betheve.restaurant.domain.entity.Restaurant;
import com.betheve.betheve.review.domain.entity.Review;
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
    public Review postReview(long restaurantId, long memberId, String content, byte score) {

//        if(repo.existsByMemberAndRestaurant(memberId, restaurantId)) {
//            throw new IllegalArgumentException("가게당 1개의 리뷰만 작성 할 수 있습니다.");
//        }

        Member author = new Member(memberId);

        Review review = author.PostReview(restaurantId, content, score);

        repo.save(review);

        return review;
    }

    @Override
    public List<Review> getAllReviewsById(long memberId) {

        return repo.findAllByMemberId(memberId);
    }

    @Override
    public void deleteReview(long memberId, long reviewId) {

        Member owner = new Member(memberId);

        repo.findByMemberIdAndReviewId(memberId, reviewId);


    }
}
