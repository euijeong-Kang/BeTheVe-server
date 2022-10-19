package com.betheve.betheve.review.api.controller;

import com.betheve.betheve.auth.domain.service.AuthService;
import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.review.domain.entity.Review;
import com.betheve.betheve.review.domain.entity.dto.PostReviewDto;
import com.betheve.betheve.review.domain.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;
    private final AuthService authService;

    @Autowired
    public ReviewController(ReviewService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @Operation(summary = "리뷰 등록", description = "식당에 리뷰를 등록합니다.")
    @PostMapping("/restaurants/{id}")
    public Review postReview(@RequestHeader (name="Authorization") String token,
                             @PathVariable(name = "id")
                                 @Parameter(in = ParameterIn.PATH, required = true, description = "레스토랑 ID", example = "1234")
                                 final long id,
                             @RequestBody
                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 정보(리뷰 내용, 평점)")
                                 final PostReviewDto postReviewDto) {

        Member author = authService.getMemberByToken(token);

        return service.postReview(id, author, postReviewDto);
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

    @Operation(summary = "리뷰 삭제", description = "작성한 리뷰를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@RequestHeader (name="Authorization") String token,
                                          @PathVariable(name = "id")
                                              @Parameter(in = ParameterIn.PATH, required = true, description = "리뷰 ID", example = "1234")
                                              final long id) {

        Member owner = authService.getMemberByToken(token);

        return service.deleteReview(owner, id) ? new ResponseEntity<>("리뷰가 성공적으로 삭제됐습니다.", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "레스토랑 리뷰항목 조회", description = "해당 음식점에 작성된 리뷰 항목을 조회합니다.")
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getAllRestaurantReviews(@PathVariable(name = "id")
                                                     @Parameter(in = ParameterIn.PATH, required = true, description = "유저 ID", example = "1234")
                                                     final long id) {

        return new ResponseEntity<>(service.getAllRestaurantReview(id), HttpStatus.OK);

    }

}
