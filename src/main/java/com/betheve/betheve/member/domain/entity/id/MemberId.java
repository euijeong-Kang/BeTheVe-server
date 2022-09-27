package com.betheve.betheve.member.domain.entity.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class MemberId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;

    private String accountId;


}
