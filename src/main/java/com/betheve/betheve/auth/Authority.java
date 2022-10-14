package com.betheve.betheve.auth;

import com.betheve.betheve.member.domain.entity.MemberAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @Column(name = "authority_name",length = 50)
    @Enumerated(EnumType.STRING)
    private MemberAuth authorityName;

    public String getAuthorityName() {
        return this.authorityName.toString();
    }
}
