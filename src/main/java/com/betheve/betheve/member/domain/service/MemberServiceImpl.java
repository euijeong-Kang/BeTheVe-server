package com.betheve.betheve.member.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.dto.MemberDto;
import com.betheve.betheve.member.domain.entity.id.MemberId;
import com.betheve.betheve.member.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(MemberId memberId){
        return memberRepository.getMemberByMemberId_MemberId(memberId).orElseThrow();
    }

    @Override
    public Member createMember(MemberDto memberDto) {

        Member newMember = new Member();

        memberRepository.save(newMember);

        return newMember;
    }

    @Override
    public void deleteMember(MemberId memberId) {
        memberRepository.deleteById(memberId);
    }
}
