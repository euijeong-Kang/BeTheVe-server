package com.betheve.betheve.member.domain.service;

import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    /**
     * 전체 회원 조회
     * @return Member list
     */
    List<Member> getAllMembers();

    /**
     * 회원 조회
     * @param memberId
     * @return 개별 회원 정보
     */
    Member getMember(long memberId);

    /**
     * 회원정보 업데이트
     * @param memberDto
     * @return 업데이트 회원 정보
     */
//    Member updateMemberInfo(long memberId, MemberDto memberDto);

    /**
     * 회원 삭제
     * @param memberId
     */
    void deleteMember(long memberId);


}
