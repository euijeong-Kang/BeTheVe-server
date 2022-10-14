package com.betheve.betheve.auth.domain.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String accessToken;

    private String refreshToken;
}
