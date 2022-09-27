package com.betheve.betheve.member.domain.repository;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.id.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId> {

    Optional<Member> getMemberByMemberId_MemberId(MemberId memberId);
}
