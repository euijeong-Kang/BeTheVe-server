package com.betheve.betheve.member.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Member")
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MemberId")
    private long memberId;

    @Column(name = "AccountId")
    private String accountId;

    @Column(name = "Password")
    private String password;

    @Column(name = "MemberName")
    private String memberName;

    @Column(name = "NicName")
    private String nickName;

    @Column(name = "Email")
    private String email;

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
