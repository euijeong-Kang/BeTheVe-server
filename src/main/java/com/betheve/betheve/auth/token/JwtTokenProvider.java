package com.betheve.betheve.auth.token;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.auth.domain.entity.Token;
import com.betheve.betheve.auth.domain.entity.dto.TokenDto;
import com.betheve.betheve.member.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    public final static long TOKEN_VALID_TIME = 30*1000*60;
    public final static long REFRESH_TOKEN_EXPIRED_SECOND = 180*1000*60;

    @Value("${SECRET_KEY}")
    private String secretKey;

    // 토큰발행에 사용하는 사인 알고리즘
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private final String AUTHORITIES_KEY = "auth";

    public static String AUTHORIZATION_HEADER = "Authorization";


    // Subject 기준 JWT 생성 매서드
    public String generateToken(String email, Set<Authority> auth,long tokenValid) {
        Member member = new Member();
        //권한 관리
        Claims claims = Jwts.claims().setSubject(email);
        claims.put(AUTHORITIES_KEY, auth.stream().map(Authority::getAuthorityName).collect(Collectors.joining(",")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValid))
                .signWith(this.generateSigningKey(), SIGNATURE_ALGORITHM)
                .compact();
    }

    /**
     * http 헤더로부터 bearer 토큰을 가져옴.
     * @param request
     * @return
     */
    public String getAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Key generateSigningKey() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        return  Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public TokenDto createTokenDto(String accessToken, String refreshToken) {
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     *
     * @param email
     * @param auth
     * @return 엑세스 토큰 생성
     */
    public String generateAccessToken(String email,Set<Authority> auth) {
        return this.generateToken(email, auth, TOKEN_VALID_TIME);
    }

    /**
     *
     * @param email
     * @param auth
     * @return 리프레시 토큰 생성
     */
    public String generateRefreshToken(String email, Set<Authority> auth) {
        return this.generateToken(email, auth, REFRESH_TOKEN_EXPIRED_SECOND);
    }

}
