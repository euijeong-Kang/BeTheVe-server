package com.betheve.betheve.advertise.recommend.api.controller;

import com.betheve.betheve.advertise.recommend.domain.entity.Recommend;
import com.betheve.betheve.advertise.recommend.domain.service.RecommendServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommends")
public class RecommendController {

    private final RecommendServiceImpl recommendService;

    @Autowired
    public RecommendController(RecommendServiceImpl recommendService) {
        this.recommendService = recommendService;
    }

    @Operation(summary = "추천식당 전체 조회", description = "모든 추천식당을 조회합니다.")
    @GetMapping
    public List<Recommend> getAllRecommends() {
        return recommendService.getAllRecommends();
    }

    @Operation(summary = "추천식당 조회", description = "추천 식당 정보를 조회합니다.")
    @GetMapping("/{id}")
    public Recommend getRecommend(@PathVariable long id) {
        return recommendService.getReCommend(id);
    }

}
