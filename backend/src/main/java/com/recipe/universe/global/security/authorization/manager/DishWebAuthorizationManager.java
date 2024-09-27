package com.recipe.universe.global.security.authorization.manager;

import com.recipe.universe.domain.dish.dish.service.DishService;
import com.recipe.universe.global.security.authorization.WebAuthorizatoinManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.function.Supplier;

@Component
public class DishWebAuthorizationManager extends AbstractWebAuthorizationManager {
    private static final String CHECK_URI_PATTERN = "/dish/**";
    private final DishService dishService;


    public DishWebAuthorizationManager(DishService dishService) {
        super(CHECK_URI_PATTERN);
        this.dishService = dishService;
    }

    @Override
    public AuthorizationDecision decide(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        return new AuthorizationDecision(false);
    }
}
