package com.recipe.universe.global.security.authorization.manager.web;

import com.recipe.universe.global.security.authorization.WebAuthorizatoinManager;
import com.recipe.universe.global.security.authorization.manager.role.AuthorizationDelegator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public abstract class AbstractWebAuthorizationManager implements WebAuthorizatoinManager {
    private final AntPathRequestMatcher matcher;
    private final AntPathMatcher extracter;
    private final String generalIdMethodPattern = "/{entity}/{id}/{method}";

    public AbstractWebAuthorizationManager() {
        this.matcher = new AntPathRequestMatcher(getPattern());
        extracter = new AntPathMatcher();
    }

    public abstract String getPattern();

    @Override
    public AuthorizationDecision decide(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        HttpMethod method = getMethod(context);
        if(method == HttpMethod.GET){
            return get(authenticationSupplier, context);
        }else {
            return post(authenticationSupplier, context);
        }
    }

    @Override
    public boolean support(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        return matcher.matches(context.getRequest());
    }

    /* You must Override these method, get() and post() */

    protected AuthorizationDecision get(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        return new AuthorizationDecision(true);
    }

    protected AuthorizationDecision post(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context){
        return new AuthorizationDecision(false);
    }

    /* UTILS */

    public HttpMethod getMethod(RequestAuthorizationContext context){
        return HttpMethod.valueOf(context.getRequest().getMethod());
    }

    public Map<String, String> extractPathVariable(String pattern, RequestAuthorizationContext context){
        return extracter.extractUriTemplateVariables(pattern, context.getRequest().getServletPath());
    }

    public Map<String, String> extractIdAndMethod(RequestAuthorizationContext context){
        try {
            return extracter.extractUriTemplateVariables(generalIdMethodPattern, context.getRequest().getServletPath());
        } catch (IllegalStateException e){
            // id/method 패턴이 아닌 경우 빈 Map을 반환한다.
            return new HashMap<>();
        }
    }

    public boolean matchUri(String pattern, RequestAuthorizationContext context){
        return extracter.match(pattern, context.getRequest().getServletPath());
    }
}
