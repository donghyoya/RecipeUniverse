package com.recipe.universe.global.security.authorization.manager.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component
public class MyPageWebAuthorizationManager extends AbstractWebAuthorizationManager{
    private static final String SUPPORT_URI_PATTERN = "/mypage/**";

    @Override
    public String getPattern() {
        return SUPPORT_URI_PATTERN;
    }
}
