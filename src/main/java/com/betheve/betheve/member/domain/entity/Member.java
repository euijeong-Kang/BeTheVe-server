package com.betheve.betheve.member.domain.entity;

import com.betheve.betheve.member.domain.entity.id.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member")
@AllArgsConstructor
public class Member {

    @EmbeddedId
    MemberId memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "email")
    private String email;

    private UserRole userRole;

    @Embedded
    private MemberAddress address;

    public Member() {}

    public boolean correctPassword(String password) {
        return !StringUtils.isEmpty(this.password) && this.password.equals(password);
    }

    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }

}
