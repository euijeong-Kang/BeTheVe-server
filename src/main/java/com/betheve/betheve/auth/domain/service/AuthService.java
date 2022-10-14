package com.betheve.betheve.auth.domain.service;

import com.betheve.betheve.auth.domain.entity.RefreshToken;
import com.betheve.betheve.auth.domain.repository.RefreshTokenRepository;
import com.betheve.betheve.auth.login.domain.dto.LoginRequestDto;
import com.betheve.betheve.auth.token.CustomEmailPasswordAuthToken;
import com.betheve.betheve.auth.token.JwtTokenProvider;
import com.betheve.betheve.auth.domain.entity.dto.TokenDto;
import com.betheve.betheve.member.domain.entity.Member;
import com.betheve.betheve.member.domain.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository,
                       CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.customUserDetailsService = customUserDetailsService;
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
}
