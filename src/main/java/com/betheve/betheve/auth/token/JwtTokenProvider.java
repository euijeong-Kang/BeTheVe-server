package com.betheve.betheve.auth.token;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.auth.domain.entity.dto.TokenDto;
import com.betheve.betheve.common.exception.BusinessException;
import com.betheve.betheve.member.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
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

    public int validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Decoders.BASE64.decode(secretKey)).build().parseClaimsJws(token);
            return 1;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            return 2;
        } catch (Exception e) {
            log.info("잘못된 토큰입니다.");
            return -1;
        }
    }

    // JWT 토큰 서브젝트 얻기 - 유효한 토큰이 아닐 경우 Null 반환
    public String getSubject(String token) {
        if (this.validateToken(token) != 1) {
            throw new BusinessException("유요하지 않은 토큰");
        }
        Claims claims = this.parseClaims(token);
        return claims.getSubject();
    }

    public Authentication getAuthentication(String accessToken) throws BusinessException {

        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null || !StringUtils.hasText(claims.get(AUTHORITIES_KEY).toString())) {
            throw new BusinessException("no Authentication"); // 유저에게 아무런 권한이 없습니다.
        }

        log.debug("claims.getAuth = {}",claims.get(AUTHORITIES_KEY));
        log.debug("claims.getEmail = {}",claims.getSubject());

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        authorities.stream().forEach(o->{
            log.debug("getAuthentication -> authorities = {}",o.getAuthority());
        });

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new CustomEmailPasswordAuthToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Decoders.BASE64.decode(secretKey))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) { // 만료된 토큰이 더라도 일단 파싱을 함
            return e.getClaims();
        }
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
