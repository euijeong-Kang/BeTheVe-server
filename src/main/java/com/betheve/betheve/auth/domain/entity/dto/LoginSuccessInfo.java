package com.betheve.betheve.auth.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginSuccessInfo {

    private String accountId;

    private String memberName;

    private String nickName;
}
