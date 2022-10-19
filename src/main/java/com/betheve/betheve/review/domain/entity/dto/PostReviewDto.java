package com.betheve.betheve.review.domain.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(force = true)
public class PostReviewDto {
    @Schema(description = "리뷰 내용", example = "최고에요!")
    private String content;

    @Schema(description = "평점", example = "5")
    private byte score;
}