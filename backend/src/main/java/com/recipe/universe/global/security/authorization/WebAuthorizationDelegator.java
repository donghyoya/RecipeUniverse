package com.recipe.universe.global.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * Request Url을 분석하여 적절한 WebAuthorizationmanager에게 위임하여 AccessDecision을 처리하는 역할
 */
@RequiredArgsConstructor
@Component
public class WebAuthorizationDelegator {
    public static AuthorizationDecision DENY = new AuthorizationDecision(false);
    private final List<WebAuthorizatoinManager> managers;

    public AuthorizationDecision decide(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        try {
            for(WebAuthorizatoinManager manager : managers){
                if(manager.support(authenticationSupplier, context)){
                    return manager.decide(authenticationSupplier, context);
                }
            }
            return DENY;
        }catch (Exception e){
            throw new AccessDeniedException(e.getMessage());
        }
    }

}
