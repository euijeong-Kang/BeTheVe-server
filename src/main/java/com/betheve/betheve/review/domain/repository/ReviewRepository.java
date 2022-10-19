package com.betheve.betheve.review.domain.repository;

import com.betheve.betheve.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMemberId(long id);

    Optional<Review> findByReviewId(long id);

    List<Review> findAllByRestaurantId(long id);
}
