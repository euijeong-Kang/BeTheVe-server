package com.betheve.betheve.member.domain.service;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.common.exception.BusinessException;
import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        log.debug("CustomUserDetailsService -> email = {}", email);
        return memberRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BusinessException("유저정보를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("유저정보를 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {

        // Collections<? extends GrantedAuthority>
        List<SimpleGrantedAuthority> authList = member.getAuthorities()
                .stream()
                .map(Authority::getAuthorityName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        authList.forEach(o-> log.debug("authList -> {}",o.getAuthority()));

        return new User(
                member.getEmail(),
                member.getPassword(),
                authList
        );
    }
}
