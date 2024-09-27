package com.recipe.universe.global.security.authorization.manager;

import com.recipe.universe.global.security.authorization.WebAuthorizatoinManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.function.Supplier;

public abstract class AbstractWebAuthorizationManager implements WebAuthorizatoinManager {
    private final String pattern;
    private final AntPathRequestMatcher matcher;

    public AbstractWebAuthorizationManager(String pattern) {
        this.pattern = pattern;
        this.matcher = new AntPathRequestMatcher(pattern);
    }

    @Override
    public boolean support(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        return matcher.matches(context.getRequest());
    }
}
