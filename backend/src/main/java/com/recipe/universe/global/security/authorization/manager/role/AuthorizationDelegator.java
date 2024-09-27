package com.recipe.universe.global.security.authorization.manager.role;

import com.recipe.universe.domain.user.role.entity.RoleName;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class AuthorizationDelegator {
    private final Map<RoleName, AuthorityAuthorizationManager> delegates;
    private final AuthenticatedAuthorizationManager authenticatedAuthorizationManager;

    public AuthorizationDelegator() {
        delegates = new HashMap<>();
        for(RoleName role : RoleName.values()){
            delegates.put(role, AuthorityAuthorizationManager.hasRole(role.getRoleName().substring(5)));
        }
        authenticatedAuthorizationManager = AuthenticatedAuthorizationManager.authenticated();
    }

    public AuthorizationDecision isAuthentication(Supplier<Authentication> authenticationSupplier){
        return authenticatedAuthorizationManager.check(authenticationSupplier, null);
    }

    public AuthorizationDecision hasRole(RoleName role, Supplier<Authentication> authenticationSupplier){
        return delegates.get(role).check(authenticationSupplier, null);
    }


}
