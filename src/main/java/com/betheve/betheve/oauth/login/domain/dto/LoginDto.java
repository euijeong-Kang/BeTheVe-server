package com.betheve.betheve.oauth.login.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {

    private String AccountId;

    private String password;

}
