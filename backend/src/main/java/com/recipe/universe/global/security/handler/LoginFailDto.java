package com.recipe.universe.global.security.handler;

import lombok.Getter;

@Getter
public class LoginFailDto {
    private String error;
    private String loginUrl;

    private static String ERROR_MESSAGE = "you need login and JWT token";

    public LoginFailDto(String loginUrl) {
        this.error = ERROR_MESSAGE;
        this.loginUrl = loginUrl;
    }
}
