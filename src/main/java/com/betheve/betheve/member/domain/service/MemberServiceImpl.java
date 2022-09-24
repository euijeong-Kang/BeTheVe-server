package com.betheve.betheve.member.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
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
    public Member getMember(long memberId){
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }
}
