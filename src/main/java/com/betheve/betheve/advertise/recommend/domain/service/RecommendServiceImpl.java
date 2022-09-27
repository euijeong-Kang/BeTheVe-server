package com.betheve.betheve.advertise.recommend.domain.service;

import com.betheve.betheve.advertise.recommend.domain.entity.Recommend;
import com.betheve.betheve.advertise.recommend.domain.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService{

    private final RecommendRepository recommendRepository;

    @Autowired
    public RecommendServiceImpl(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

    public List<Recommend> getAllRecommends() {
        return recommendRepository.findAll();
    }

    public Recommend getReCommend(long recommendId) {
        return recommendRepository.findById(recommendId).orElseThrow();
    }

}
