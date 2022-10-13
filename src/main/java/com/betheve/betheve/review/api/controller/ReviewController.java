package com.betheve.betheve.review.api.controller;

import com.betheve.betheve.member.domain.entity.id.MemberId;
import com.betheve.betheve.review.domain.entity.Review;
import com.betheve.betheve.review.domain.entity.command.PostReview;
import com.betheve.betheve.review.domain.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @Operation(summary = "리뷰 등록", description = "식당에 리뷰를 등록합니다.")
    @PostMapping("/{restaurant}/{member}")
    public Review postReview(@PathVariable(name = "restaurant")
                                 @Parameter(in = ParameterIn.PATH, required = true, description = "레스토랑 ID", example = "1234")
                                 final long restaurant,
                             @PathVariable(name = "member")
                             @Parameter(in = ParameterIn.PATH, required = true, description = "멤버 ID", example = "1234")
                             final long member,
                             @RequestBody
                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 정보(리뷰 내용, 평점)")
                                 final PostReview postReview) {

        return service.postReview(restaurant, member, postReview.getContent(), postReview.getScore());
    }

    @Operation(summary = "사용자 작성 리뷰 조회", description = "사용자가 작성한 전체 리뷰를 조회합니다.")
    @GetMapping("/members/{id}")
    public ResponseEntity<?> getAllMemberReviews(@PathVariable(name = "id")
                                   @Parameter(in = ParameterIn.PATH, required = true, description = "유저 ID", example = "1234")
                                   final long id) {

        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("error", false);
        responseMap.put("reviewList", service.getAllReviewsById(id));

        return ResponseEntity.ok(responseMap);
    }


}
