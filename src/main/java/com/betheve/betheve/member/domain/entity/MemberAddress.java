package com.betheve.betheve.member.domain.entity;

import com.betheve.betheve.address.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class MemberAddress extends Address {



}
