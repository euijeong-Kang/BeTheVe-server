package com.betheve.betheve.member.domain.entity;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.review.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "member")
@AllArgsConstructor
public class Member {

    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "member_name", length = 50, nullable = false)
    private String memberName;

    @Column(name = "nickname", unique = true)
    private String nickName;

    @Embedded
    private MemberAddress address;

//    @OneToMany
//    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities = new HashSet<>();

    public Member() {}

    public Member(String email, String password, String memberName, String nickName, Set<Authority> authorities) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.nickName = nickName;
        this.authorities = authorities;
    }

    public Review PostReview(long restaurantId, String content, byte score) {
        return Review.builder()
                .memberId(this.memberId)
                .restaurantId(restaurantId)
                .content(content)
                .score(score)
                .build();
    }

    public boolean hasOwnershipOf(Review review) {

        return this.memberId == review.getMemberId();
    }

}
