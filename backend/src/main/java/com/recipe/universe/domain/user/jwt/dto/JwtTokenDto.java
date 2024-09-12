package com.recipe.universe.domain.user.jwt.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class JwtTokenDto {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Date accessExpiration;
    private Date refreshExpiration;

    public JwtTokenDto(String accessToken, String refreshToken, Date accessExpiration, Date refreshExpiration) {
        this.grantType = "Bearer";
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
    }
}
