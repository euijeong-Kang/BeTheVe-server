package com.betheve.betheve.member.domain.entity.dto;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.member.domain.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Schema
@Getter @Setter
public class MemberDto {

    private String email;

    private String password;

    private String memberName;

    private String nickName;

    public Member toMember(PasswordEncoder passwordEncoder,  Set<Authority> authorities) {
        return new Member(email,  passwordEncoder.encode(password), memberName, nickName, authorities);
    }




}
