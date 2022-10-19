package com.betheve.betheve.auth.domain.service;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.auth.domain.entity.RefreshToken;
import com.betheve.betheve.auth.domain.repository.AuthorityRepository;
import com.betheve.betheve.auth.domain.repository.RefreshTokenRepository;
import com.betheve.betheve.auth.domain.entity.dto.LoginRequestDto;
import com.betheve.betheve.auth.token.CustomEmailPasswordAuthToken;
import com.betheve.betheve.auth.token.JwtTokenProvider;
import com.betheve.betheve.auth.domain.entity.dto.TokenDto;
import com.betheve.betheve.common.exception.BusinessException;
import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.entity.MemberAuth;
import com.betheve.betheve.member.domain.entity.dto.MemberDto;
import com.betheve.betheve.member.domain.entity.dto.MemberMapper;
import com.betheve.betheve.member.domain.repository.MemberRepository;
import com.betheve.betheve.member.domain.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository,
                       CustomUserDetailsService customUserDetailsService, AuthorityRepository authorityRepository,
                       MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.authorityRepository = authorityRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberDto signup(MemberDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new BusinessException("이미 가입된 회원정보");
        }

        // DB 에서 ROLE_USER를 찾아서 권한으로 추가한다.
        Authority authority = authorityRepository
                .findByAuthorityName(MemberAuth.ROLE_USER).orElseThrow(()->new BusinessException("해당 권한 DB 정보 없음"));

        Set<Authority> set = new HashSet<>();
        set.add(authority);


        Member member = memberRequestDto.toMember(passwordEncoder,set);
        log.debug("member = {}",member);
        return MemberMapper.INSTANCE.toDto(memberRepository.save(member));
    }

    @Transactional
    public TokenDto login(LoginRequestDto loginRequestDto) {
        CustomEmailPasswordAuthToken customEmailPasswordAuthToken = new CustomEmailPasswordAuthToken(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(customEmailPasswordAuthToken);
        String email = authenticate.getName();
        Member member = customUserDetailsService.getMember(email);

        String accessToken = tokenProvider.generateAccessToken(email, member.getAuthorities());
        String refreshToken = tokenProvider.generateRefreshToken(email, member.getAuthorities());

        //refresh Token 저장
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .key(email)
                        .value(refreshToken)
                        .build()
        );

        return tokenProvider.createTokenDto(accessToken, refreshToken);

    }

    @Transactional
    public Member getMemberByToken(String accessToken) {

        String email = tokenProvider.getSubject(accessToken);

        return customUserDetailsService.getMember(email);
    }
}
