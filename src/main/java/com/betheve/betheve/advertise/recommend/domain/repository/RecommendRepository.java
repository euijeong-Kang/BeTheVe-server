package com.betheve.betheve.advertise.recommend.domain.repository;

import com.betheve.betheve.advertise.recommend.domain.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}
