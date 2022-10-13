package com.betheve.betheve.review.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "review")
@Embeddable
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewId;

    private long memberId;

    private long restaurantId;

    private String content;

    private byte score;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdatedDate;

    @Builder
    public Review(long memberId, long restaurantId, String content, byte score) {
        this.memberId = memberId;
        this.restaurantId = restaurantId;
        this.content = content;
        this.score = score;
        this.createdDate = LocalDateTime.now();
        this.lastUpdatedDate = LocalDateTime.now();
    }

}
