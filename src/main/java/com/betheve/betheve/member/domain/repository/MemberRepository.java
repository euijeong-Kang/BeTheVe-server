package com.betheve.betheve.member.domain.repository;

import com.betheve.betheve.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> getMemberByMemberId(long memberId);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
