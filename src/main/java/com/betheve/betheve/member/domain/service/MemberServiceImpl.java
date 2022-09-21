package com.betheve.betheve.member.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.dto.MemberDto;
import com.betheve.betheve.member.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }
}
