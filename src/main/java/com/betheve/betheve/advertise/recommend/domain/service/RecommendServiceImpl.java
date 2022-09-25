package com.betheve.betheve.advertise.recommend.domain.service;

import com.betheve.betheve.advertise.recommend.domain.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService{

    private final RecommendRepository recommendRepository;

    @Autowired
    public RecommendServiceImpl(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

}
