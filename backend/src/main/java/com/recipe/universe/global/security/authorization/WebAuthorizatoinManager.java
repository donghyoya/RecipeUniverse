package com.recipe.universe.global.security.authorization;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public interface WebAuthorizatoinManager {
    public AuthorizationDecision decide(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context);

    public boolean support(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context);
}
