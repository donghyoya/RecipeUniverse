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
    protected AuthorizationDecision post(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        if(this.matchUri("/mypage/info/**", context)){
            return new AuthorizationDecision(true);
        }else {
            return super.post(authenticationSupplier, context);
        }
    }

    @Override
    public String getPattern() {
        return SUPPORT_URI_PATTERN;
    }
}
